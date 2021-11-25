package alex.talamus.currencypredictor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CurrencyPredictorApplication

fun main(args: Array<String>) {
    runApplication<CurrencyPredictorApplication>(*args)
}
