package alex.talamus.currencypredictor.services

import alex.talamus.currencypredictor.event.MessageEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.lang.IllegalStateException

@Service
class ReceiverService(
    private val applicationEventPublisher: ApplicationEventPublisher,
) {

    fun execute(update: Update){
        if (update.hasCallbackQuery()) {
            println("callback execute")
            callbackExecute(update.callbackQuery)
        } else if (update.hasMessage()) {
            messageExecute(update.message)
        } else {
            throw IllegalStateException("Not yet supported")
        }
    }

    private fun callbackExecute(callbackQuery: CallbackQuery) {
        println("callback")
    //    val chatId = callbackQuery.from.id
    }

    private fun messageExecute(message: Message) {
        val chatId = message.chatId
        applicationEventPublisher.publishEvent(
            MessageEvent(chatId)
        )
    }

}