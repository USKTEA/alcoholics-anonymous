package com.oop.sample.sample.endpoints

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ResultRouter {
    private val basePath = "/results"

    @Bean
    fun numberRoute(handler: ResultHandler) = coRouter {
        path(basePath).nest {
            GET("/{resultId}", handler::getResult)
            POST("", handler::createResult)
        }
    }
}
