package alex.talamus.currencypredictor.commands

import alex.talamus.currencypredictor.enums.Command
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class InfoGameCommand: AbstractCommand(Command.INFO_GAME) {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        sendEvent(chat.id, arguments)
    }
}