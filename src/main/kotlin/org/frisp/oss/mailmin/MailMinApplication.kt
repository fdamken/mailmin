package org.frisp.oss.mailmin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class MailMinApplication

fun main(args: Array<String>) {
    runApplication<MailMinApplication>(*args)
}
