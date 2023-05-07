package com.usktea.functionalendpoint.endpoint

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TaskRouter {
    private val basePath = "/tasks"

    @Bean
    fun taskRoutes(handler: TaskHandler) = coRouter {
        path(basePath).nest {
            GET("", handler::lists)
        }
    }
}