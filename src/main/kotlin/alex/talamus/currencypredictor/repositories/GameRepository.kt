package alex.talamus.currencypredictor.repositories

import alex.talamus.currencypredictor.domain.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository: JpaRepository<Game, Long>