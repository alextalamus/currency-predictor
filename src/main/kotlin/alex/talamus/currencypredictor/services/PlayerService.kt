package alex.talamus.currencypredictor.services

import alex.talamus.currencypredictor.domain.Player
import alex.talamus.currencypredictor.repositories.PlayerRepository
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerRepository: PlayerRepository
) {

    fun existPlayerById(id: Long) = playerRepository.existsById(id)

    fun findPlayerById(id: Long) = playerRepository.findById(id)

    fun saveNewPlayer(id: Long, name: String) = playerRepository
        .save(Player(id = id, name = name))

    fun updatePlayer(player: Player) = playerRepository.save(player)

}