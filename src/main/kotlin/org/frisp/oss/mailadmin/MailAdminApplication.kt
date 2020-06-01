package org.frisp.oss.mailadmin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MailAdminApplication

fun main(args: Array<String>) {
    runApplication<MailAdminApplication>(*args)
}
