package me.nort3x.test2

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
class BuggyTransaction {

    @Id
    @GeneratedValue(generator = "payment_uuid")
    @GenericGenerator(name = "payment_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false)
    var uuid: UUID? = null
}

@Entity
class BuggyInvoice(
    @MapsId @OneToOne val outGoingTransaction: BuggyTransaction
) {
    @Id
    var uuid: UUID? = null

    @OneToOne
    var inComingTransaction: BuggyTransaction? = null

}