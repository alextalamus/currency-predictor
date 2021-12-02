package alex.talamus.currencypredictor.listener

import alex.talamus.currencypredictor.enums.Command
import alex.talamus.currencypredictor.event.CallbackEvent
import alex.talamus.currencypredictor.event.CommandEvent
import alex.talamus.currencypredictor.logic.Logic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationController(
    private val logic: Logic
) {

    inner class CommandEventListener {
        @EventListener
        fun onApplicationEvent(event: CommandEvent) {
            when (event.command) {
                Command.CREATE -> logic.createNewGame(event.chatId, event.arguments)
                Command.INFO_PLAYER -> logic.getInfo(event.chatId, event.arguments)
                Command.START -> logic.createNewPlayer(event.chatId, event.arguments)
                Command.PSEUDO -> logic.changePseudo(event.chatId, event.arguments)
                Command.JOIN -> logic.joinGame(event.chatId, event.arguments)
                Command.INFO_GAME -> logic.getInfoGame(event.chatId, event.arguments)
                Command.BEGIN -> logic.beginGame(event.chatId, event.arguments)
                Command.END -> logic.endGame(event.chatId, event.arguments)
            }
        }
    }

    @Bean
    @Lazy
    fun commandEventListenerBean(): CommandEventListener = CommandEventListener()

}