package com.usktea.circult_breaker.runner

import com.usktea.circult_breaker.service.CircuitBreakerStatusService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class CircuitBreakerRunner(private val circuitBreakerStatusService: CircuitBreakerStatusService) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        circuitBreakerStatusService.printCircuitBreakerStatus()
    }
}
