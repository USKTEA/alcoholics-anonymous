package com.usktea.circult_breaker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class CircultBreakerApplication

fun main(args: Array<String>) {
	runApplication<CircultBreakerApplication>(*args)
}
