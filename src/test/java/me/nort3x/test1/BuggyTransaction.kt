package me.nort3x.test1

import javax.persistence.*

@Entity
class BuggyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    var uuid: Long? = null
}

@Entity
class BuggyInvoice(
    @MapsId @OneToOne val outGoingTransaction: BuggyTransaction
) {
    @Id
    var uuid: Long? = null

    @OneToOne
    var inComingTransaction: BuggyTransaction? = null

}