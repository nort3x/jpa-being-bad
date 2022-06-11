package me.nort3x.test2

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Component

class BuggyTransactionWrapper {

    @Transactional
    fun inDefaultTransaction(func: () -> Unit) {
        func()
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun inReadCommittedTransaction(func: () -> Unit) {
        func()
    }
}