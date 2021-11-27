package alex.talamus.currencypredictor.bot

import alex.talamus.currencypredictor.domain.Message
import alex.talamus.currencypredictor.repositories.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


@Component
class Bot: TelegramLongPollingBot() {

    @Autowired
    lateinit var repository: MessageRepository

    @Value("\${bot.name}")
    lateinit var username: String

    @Value("\${bot.token}")
    lateinit var token: String

    override fun getBotUsername(): String {
        return username
    }

    override fun getBotToken(): String {
        return token
    }

    override fun onUpdateReceived(update: Update) {
        try {
            execute(SendMessage
                .builder()
                .chatId(update.message.chatId.toString())
                .text("${update.message.chatId}: ${update.message.text}")
                .build()
            )
            repository.save(Message(update.message.text))
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}