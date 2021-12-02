package alex.talamus.currencypredictor.domain

import alex.talamus.currencypredictor.enums.Status
import javax.persistence.*

@Entity
@Table(name = "PLAYERS")
data class Player(
    @Id
    val id: Long,
    val name: String,
    var pseudonym: String = name,
    var inAction: Boolean = false,
    var numberOfPoints: Int = 0,
    //@Enumerated(value = EnumType.STRING)
    var status: Status = Status.REST,
    var master: Boolean = false,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var game: Game? = null
) {

    fun addGame(game: Game) {
        this.game = game
        this.inAction = true
        this.numberOfPoints = 0
        this.status = Status.CIVIC
        this.master = true
        game.players.add(this)
    }

    fun joinGame(game: Game) {
        this.game = game
        this.inAction = true
        this.numberOfPoints = 0
        this.status = Status.CIVIC
        this.master = false
        game.players.add(this)
    }
}