package com.usktea.circuit.demo.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfiguration(
    private val circuitBreakerProperties: CircuitBreakerProperties
) {
    @Bean
    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
        return CircuitBreakerRegistry.ofDefaults()
    }

    @Bean
    fun circuitBreaker(registry: CircuitBreakerRegistry): CircuitBreaker {
        val config = CircuitBreakerConfig.custom()
            .failureRateThreshold(circuitBreakerProperties.failureRateThreshold)
            .slowCallDurationThreshold(Duration.ofMillis(circuitBreakerProperties.slowCallDurationThreshold))
            .slowCallRateThreshold(circuitBreakerProperties.slowCallRateThreshold)
            .waitDurationInOpenState(Duration.ofMillis(circuitBreakerProperties.waitDurationInOpenState))
            .minimumNumberOfCalls(circuitBreakerProperties.minimumNumberOfCalls)
            .slidingWindowSize(circuitBreakerProperties.slidingWindowSize)
            .permittedNumberOfCallsInHalfOpenState(circuitBreakerProperties.permittedNumberOfCallsInHalfOpenState)
            .build()

        val circuitBreaker = registry.circuitBreaker(
            "myCircuitBreaker", config
        )

        circuitBreaker.eventPublisher.onStateTransition {
            println("State transition: ${it.stateTransition}")
        }

        return circuitBreaker
    }
}
