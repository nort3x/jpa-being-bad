package me.nort3x.test2

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import java.util.UUID


@SpringBootTest
@SpringBootConfiguration
@ComponentScan
@EnableAutoConfiguration
@ActiveProfiles("test")
class Reproduce {

    @Autowired
    lateinit var buggyTransactionWrapper: BuggyTransactionWrapper

    @Autowired
    lateinit var buggyService: BuggyService

    @Test
    fun `Transaction Is Committed and i Can't Read the Data - will fail`(){
        buggyTransactionWrapper.inDefaultTransaction {
            val invoice = buggyService.createInvoice()
            assertDoesNotThrow { buggyService.invoiceRepo.findById(invoice.uuid!!).orElseThrow() }
        }

        var uuid: UUID? = null
        buggyTransactionWrapper.inDefaultTransaction {
            val invoice = buggyService.createInvoice()
            uuid = invoice.uuid
        }
        buggyTransactionWrapper.inDefaultTransaction {
            assertDoesNotThrow { buggyService.invoiceRepo.findById(uuid!!).orElseThrow() }
        }

    }
}