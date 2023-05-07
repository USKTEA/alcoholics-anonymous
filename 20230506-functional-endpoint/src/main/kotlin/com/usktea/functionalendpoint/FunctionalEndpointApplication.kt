package com.usktea.functionalendpoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FunctionalEndpointApplication

fun main(args: Array<String>) {
    runApplication<FunctionalEndpointApplication>(*args)
}
