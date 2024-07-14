package com.usktea.circuit.demo.service

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import java.util.Random
import org.springframework.stereotype.Service

@Service
class SimpleService(
    private val circuitBreakerRegistry: CircuitBreakerRegistry
) {

    @CircuitBreaker(
        name = "myCircuitBreaker",
        fallbackMethod = "simpleFallback"
    )
    fun simple(failurePercent: Int, delay: Int): List<String> {
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

        Thread.sleep(delay.toLong())
        val random = Random().nextInt(0, 10)

        println(random)
        if (failurePercent > random) {
            throw RuntimeException("Exceed")
        }

        return listOf("Goooooooooob boy")
    }

    fun simpleFallback(failurePercent: Int, delay: Int, exception: Throwable): List<String> {
        println(exception.message)
        println("Executing fallback method")
        return emptyList()
    }
}
