package alex.talamus.currencypredictor.repositories

import alex.talamus.currencypredictor.domain.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository: JpaRepository<Player, Long>{

    override fun existsById(chatId: Long): Boolean
}