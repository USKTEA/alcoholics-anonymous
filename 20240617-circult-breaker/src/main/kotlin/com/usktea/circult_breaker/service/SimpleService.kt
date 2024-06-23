package com.usktea.circult_breaker.service

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import java.util.Random
import org.springframework.stereotype.Service

@Service
class SimpleService(
    private val circuitBreakerStatusService: CircuitBreakerStatusService,
) {

    @CircuitBreaker(
        name = "simple",
        fallbackMethod = "simpleFallback"
    )
    fun simple(failurePercent: Int, delay: Int): List<String> {
        circuitBreakerStatusService.printCircuitBreakerStatus()

        Thread.sleep(delay.toLong())
        val random = Random().nextInt(0, 10)

        if (failurePercent > random) {
            throw RuntimeException("Exceed")
        }

        return listOf("Goooooooooob boy")
    }

    fun simpleFallback(failurePercent: Int, delay: Int, exception: Throwable): List<String> {
        println("Executing fallback method")
        return emptyList()
    }
}
