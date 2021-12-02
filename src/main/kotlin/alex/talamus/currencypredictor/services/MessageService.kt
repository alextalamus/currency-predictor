package alex.talamus.currencypredictor.services

import alex.talamus.currencypredictor.bot.Bot
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton


@Service
class MessageService (
    private val bot: Bot,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun sendMessageToBot(chatId: Long, text: String) {
        bot.execute(simpleTextMessage(chatId, text))
    }

    private fun simpleTextMessage(chatId: Long, text: String): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId.toString()
        sendMessage.text = text
        return sendMessage
    }





}
