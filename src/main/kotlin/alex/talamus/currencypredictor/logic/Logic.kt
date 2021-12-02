package alex.talamus.currencypredictor.logic

import alex.talamus.currencypredictor.enums.Status
import alex.talamus.currencypredictor.services.GameService
import alex.talamus.currencypredictor.services.MessageService
import alex.talamus.currencypredictor.services.PlayerService
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Component
class Logic(
    private val gameService: GameService,
    private val playerService: PlayerService,
    private val messageService: MessageService
) {
    @Autowired
    lateinit var logger: Logger

    //TODO разбить класс на подклассы по типу действия: информационный, игровой, инициализации

    // Создание нового пользователя (игрока)
    fun createNewPlayer(chatId: Long, arguments: Array<out String>) {
        if (!playerService.existPlayerById(chatId)){
            val player = playerService.saveNewPlayer(chatId, arguments.first())
            val notification = "Created new player. id: ${player.id}, name: ${player.name}"
            messageService.sendMessageToBot(chatId, notification)
            logger.info(notification)
        }
    }

    // Передача информации о пользователе
    fun getInfo(chatId: Long, arguments: Array<out String>) {
        val notification = if (playerService.existPlayerById(chatId)){
            val player = playerService.findPlayerById(chatId).get()
            "Player info:\n" +
                    "id: ${player.id}\n" +
                    "name: ${player.name}\n" +
                    "pseudonym: ${player.pseudonym}\n" +
                    "status: ${player.status}\n" +
                    "master: ${player.master}\n" +
                    "number of points: ${player.numberOfPoints}"
        } else {
            "User id: $chatId does not exist in the database"
        }
        messageService.sendMessageToBot(chatId, notification)
        logger.info(notification)
    }

    // Передача информации о текущей игре
    fun getInfoGame(chatId: Long, arguments: Array<out String>) {
        val notification = if (playerService.existPlayerById(chatId)){
            val player = playerService.findPlayerById(chatId).get()
            if (player.game != null) {
                val game = player.game
                "Game info:\nid: ${game!!.id}\nPlayers:\n" +
                        game.players.map { it.pseudonym }.joinToString("\n")
            } else {
                "User $chatId is not in the game"
            }
        } else {
            "User id: $chatId does not exist in the database"
        }
        messageService.sendMessageToBot(chatId, notification)
        logger.info(notification)
    }

    // Изменение псевдонима игрока
    fun changePseudo(chatId: Long, arguments: Array<out String>) {
        val playerOptional = playerService.findPlayerById(chatId)
        val notification = if (playerOptional.isPresent) {
            val player = playerOptional.get()
            player.pseudonym = arguments.first()
            val updatedPlayer = playerService.updatePlayer(player)
            "Player ${player.id} changed pseudo to ${updatedPlayer.pseudonym}"
        } else {
            "User id: $chatId does not exist in the database"
        }
        messageService.sendMessageToBot(chatId, notification)
        logger.info(notification)
    }

    // Создание игры
    fun createNewGame(chatId: Long, arguments: Array<out String>) {
        val playerOptional = playerService.findPlayerById(chatId)
        val notification = if (playerOptional.isPresent) {
            val player = playerOptional.get()
            val game = gameService.createGame(player)
            player.addGame(game)
            playerService.updatePlayer(player)
            playerService.updatePlayer(player)
            "Created game id: ${game.id}"
        } else {
            "User id: $chatId does not exist in the database"
        }
        messageService.sendMessageToBot(chatId, notification)
        logger.info(notification)
    }

    // Присоединение к игре
    fun joinGame(chatId: Long, arguments: Array<out String>) {
        var notification = "Joined to the game id: ${arguments.first()}"

        val playerOptional = playerService.findPlayerById(chatId)
        if (!playerOptional.isPresent) notification = "User id: $chatId does not exist in the database"
        else
        {
            val gameId = arguments.first().toLongOrNull()
            if (gameId == null) notification = "Argument ${arguments.first()} is not a number"
            else {
                val gameOptional = gameService.findGameById(gameId)
                if (!gameOptional.isPresent) notification = "Game id: $gameId does not exist in the database"
                else {
                    val game = gameOptional.get()
                    val player = playerOptional.get()
                    player.joinGame(game)
                    playerService.updatePlayer(player)
                }
            }
        }

        messageService.sendMessageToBot(chatId, notification)
        logger.info(notification)
    }

    // Запуск игры
    fun beginGame(chatId: Long, arguments: Array<out String>) {
        val playerOptional = playerService.findPlayerById(chatId)
        val notification = if (playerOptional.isPresent) {
            val player = playerOptional.get()
            if (player.master && player.game != null) {
                val game = player.game

                // Выбор произвольного шпиона
                val spy = game!!.players.random()
                spy.status = Status.SPY
                playerService.updatePlayer(spy)
                // Выбор произвольной локации
                // TODO сделать логику выбора локации
                val location = "Больница"
                game.location = location
                game.isActive = true
                gameService.updateGame(game)
                // Уведомление игроков о начале игры
                val message: String = "Game id: ${game.id} has started\n" +
                        "Location is: $location\n" +
                        "You are a "
                for (user in game.players) {
                    messageService.sendMessageToBot(user.id, message + user.status)
                }
                "Game id: ${game.id} has started"
            } else {
                "User does not have the right to begin a game"
            }
        } else {
            "User id: $chatId does not exist in the database"
        }

        logger.info(notification)
    }

    // Окончание игры
    fun endGame(chatId: Long, arguments: Array<out String>) {

    }

}

