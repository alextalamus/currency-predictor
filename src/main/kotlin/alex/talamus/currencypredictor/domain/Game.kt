package alex.talamus.currencypredictor.domain

import javax.persistence.*

@Entity
@Table(name = "GAMES")
data class Game(
    var previousPlayer: Long,
    var isActive: Boolean = false,
    var location: String? = null,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    val players: MutableList<Player> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
}