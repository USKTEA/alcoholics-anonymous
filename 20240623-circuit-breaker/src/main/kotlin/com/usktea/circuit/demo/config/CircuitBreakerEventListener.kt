package com.usktea.circuit.demo.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnErrorEvent
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class CircuitBreakerEventListener(
    private val circuitBreakerRegistry: CircuitBreakerRegistry
) {
    @PostConstruct
    fun initializeCircuitBreakerEventListeners() {
        circuitBreakerRegistry.allCircuitBreakers.forEach { circuitBreaker ->
            circuitBreaker.eventPublisher.onEvent { event ->
                handleCircuitBreakerEvent(event)
            }
        }
    }

    private fun handleCircuitBreakerEvent(event: CircuitBreakerEvent) {
        when (event) {
            is CircuitBreakerOnErrorEvent -> {
                println("CircuitBreakerOnErrorEvent: ${event.throwable.message}")
            }

            is CircuitBreakerOnStateTransitionEvent -> {
                println("CircuitBreakerOnStateTransitionEvent: ${event.stateTransition}")
            }

            else -> {
                println("CircuitBreakerEvent: ${event.eventType}")
            }
        }
    }
}
