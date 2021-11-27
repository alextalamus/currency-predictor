package alex.talamus.currencypredictor.domain

import javax.persistence.*

@Entity
@Table(name = "MESSAGE")
class Message(
    val message: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
}