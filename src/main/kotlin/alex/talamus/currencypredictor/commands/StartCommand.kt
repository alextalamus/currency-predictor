package alex.talamus.currencypredictor.commands

import alex.talamus.currencypredictor.enums.Command
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class StartCommand: AbstractCommand(Command.START) {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val userFullName = "${user.firstName} ${user.lastName}"
        sendEvent(chat.id, arrayOf(userFullName))
    }
}