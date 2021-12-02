package alex.talamus.currencypredictor.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class BotProperty(
    @Value("\${bot.name}")
    var username: String,

    @Value("\${bot.token}")
    var token: String
)
