package me.nort3x.test2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.UUID

@Repository
interface BuggyTransactionRepo : JpaRepository<BuggyTransaction, UUID> {}

@Repository
interface BuggyInvoiceRepo : JpaRepository<BuggyInvoice, UUID> {}

@Service
class BuggyService {

    @Autowired
    lateinit var transactionRepo: BuggyTransactionRepo

    @Autowired
    lateinit var invoiceRepo: BuggyInvoiceRepo


    fun createTransaction(): BuggyTransaction {
        return transactionRepo.save(BuggyTransaction())
    }


    fun createInvoice(): BuggyInvoice {
        return invoiceRepo.save(
            BuggyInvoice(
                createTransaction()
            )
        )
    }

}