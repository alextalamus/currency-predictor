package alex.talamus.currencypredictor.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun logger() = LoggerFactory.getLogger(javaClass)
}