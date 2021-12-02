package alex.talamus.currencypredictor.event

import alex.talamus.currencypredictor.enums.Command

class CommandEvent(
    val chatId: Long,
    val command: Command,
    val arguments: Array<out String>
) {
}