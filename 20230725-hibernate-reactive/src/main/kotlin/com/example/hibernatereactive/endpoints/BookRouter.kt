package com.example.hibernatereactive.endpoints

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class BookRouter {
    private val basePath = "/books"

    @Bean
    fun taskRoutes(handler: BookHandler) = coRouter {
        path(basePath).nest {
            GET("", handler::getAll)
        }
    }
}