package com.usktea.circult_breaker.service

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.stereotype.Service


@Service
class CircuitBreakerStatusService(private val circuitBreakerRegistry: CircuitBreakerRegistry) {

    fun printCircuitBreakerStatus() {
        val circuitBreakers = circuitBreakerRegistry.allCircuitBreakers

        circuitBreakers.forEach { circuitBreaker ->
            println("CircuitBreaker: ${circuitBreaker.name}")
            println("State: ${circuitBreaker.state}")
            println("Failure Rate: ${circuitBreaker.metrics.failureRate}")
            println("Slow Call Rate: ${circuitBreaker.metrics.slowCallRate}")
            println("Buffered Calls: ${circuitBreaker.metrics.numberOfBufferedCalls}")
            println("Failed Calls: ${circuitBreaker.metrics.numberOfFailedCalls}")
            println("Slow Calls: ${circuitBreaker.metrics.numberOfSlowCalls}")
            println("Not Permitted Calls: ${circuitBreaker.metrics.numberOfNotPermittedCalls}")
            println("Success Calls: ${circuitBreaker.metrics.numberOfSuccessfulCalls}")
            println("================================================")
        }
    }
}
