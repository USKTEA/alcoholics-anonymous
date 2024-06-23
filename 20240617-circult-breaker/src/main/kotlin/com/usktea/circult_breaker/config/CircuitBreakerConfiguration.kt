package com.usktea.circult_breaker.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfiguration(
    private val circuitBreakerProperty: CircuitBreakerProperty,
) {

    @Bean
    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
        return CircuitBreakerRegistry.ofDefaults()
    }

    @Bean
    fun circuitBreaker(registry: CircuitBreakerRegistry): CircuitBreaker {
        val config = CircuitBreakerConfig.custom().failureRateThreshold(circuitBreakerProperty.failureRateThreshold)
            .slowCallDurationThreshold(Duration.ofMillis(circuitBreakerProperty.slowCallDurationThreshold))
            .slowCallRateThreshold(circuitBreakerProperty.slowCallRateThreshold)
            .waitDurationInOpenState(Duration.ofMillis(circuitBreakerProperty.waitDurationInOpenState))
            .minimumNumberOfCalls(circuitBreakerProperty.minimumNumberOfCalls)
            .slidingWindowSize(circuitBreakerProperty.slidingWindowSize)
            .permittedNumberOfCallsInHalfOpenState(circuitBreakerProperty.permittedNumberOfCallsInHalfOpenState)
            .build()

        return registry.circuitBreaker(
            "simple", config
        )
    }
}
