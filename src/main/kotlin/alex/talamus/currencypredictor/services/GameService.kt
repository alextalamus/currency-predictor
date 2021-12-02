package alex.talamus.currencypredictor.services

import alex.talamus.currencypredictor.domain.Game
import alex.talamus.currencypredictor.domain.Player
import alex.talamus.currencypredictor.repositories.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService (
    private val gameRepository: GameRepository
) {

    fun createGame(player: Player) = gameRepository.save(Game(player.id, false, null, mutableListOf(player)))

    fun findGameById(id: Long) = gameRepository.findById(id)

    fun updateGame(game: Game) = gameRepository.save(game)
}