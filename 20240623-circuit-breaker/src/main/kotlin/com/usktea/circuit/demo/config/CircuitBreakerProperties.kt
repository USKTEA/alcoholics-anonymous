package com.usktea.circuit.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "resilience4j.circuitbreaker")
data class CircuitBreakerProperties(
    val failureRateThreshold: Float,
    val slowCallDurationThreshold: Long,
    val slowCallRateThreshold: Float,
    val waitDurationInOpenState: Long,
    val minimumNumberOfCalls: Int,
    val slidingWindowSize: Int,
    val permittedNumberOfCallsInHalfOpenState: Int,
)
