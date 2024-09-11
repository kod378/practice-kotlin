package org.delivery.storeadmin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdminApiApplication

fun main(args: Array<String>) {
    runApplication<AdminApiApplication>(*args)
}