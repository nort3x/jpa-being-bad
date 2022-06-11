package me.nort3x.test1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
interface BuggyTransactionRepo : JpaRepository<BuggyTransaction, Long> {}

@Repository
interface BuggyInvoiceRepo : JpaRepository<BuggyInvoice, Long> {}

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