package alex.talamus.currencypredictor.bot

import alex.talamus.currencypredictor.properties.BotProperty
import alex.talamus.currencypredictor.services.ReceiverService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import javax.annotation.PostConstruct


@Component
class Bot(
    private val botProperty: BotProperty,
    private val botCommands: List<IBotCommand>,
    private val receiverService: ReceiverService
): TelegramLongPollingCommandBot() {

    @PostConstruct
    fun initCommands() {
        botCommands.forEach {
            register(it)
        }

        registerDefaultAction { absSender, message ->

            val commandUnknownMessage = SendMessage()
            commandUnknownMessage.chatId = message.chatId.toString()
            commandUnknownMessage.text = "Command '${message.text}' unknown"

            absSender.execute(commandUnknownMessage)
        }
    }

    override fun processNonCommandUpdate(update: Update) {


//        val message = update.message
//        val chatId = message.chatId
//
//        val responseMessage = SendMessage()
//        responseMessage.enableMarkdown(true)
//        responseMessage.replyMarkup = getReplyMarkup(
//            listOf(
//                listOf("Кнопка 1", "Кнопка 2"),
//                listOf("Кнопка 3", "Кнопка 4")
//            )
//        )
//
//        execute(responseMessage)
        receiverService.execute(update)


    }

    private fun getReplyMarkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.keyboard = allButtons.map { rowButtons ->
            val row = KeyboardRow()
            rowButtons.forEach { rowButton -> row.add(rowButton) }
            row
        }
        return markup
    }


    override fun getBotUsername(): String = botProperty.username

    override fun getBotToken(): String = botProperty.token


}