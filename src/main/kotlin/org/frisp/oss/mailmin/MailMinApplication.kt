package org.frisp.oss.mailmin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MailMinApplication

fun main(args: Array<String>) {
    runApplication<MailMinApplication>(*args)
}
