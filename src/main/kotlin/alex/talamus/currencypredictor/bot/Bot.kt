package alex.talamus.currencypredictor.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


@Component
class Bot: TelegramLongPollingBot() {

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
                .text("$username: ${update.message.text}")
                .build()
            )
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}