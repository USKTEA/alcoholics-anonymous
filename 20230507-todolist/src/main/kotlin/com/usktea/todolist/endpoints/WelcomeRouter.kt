package com.usktea.todolist.endpoints

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WelcomeRouter {
    val basePath = ""

    @Bean
    fun welcomeRoutes(handler: WelcomeHandler) = coRouter {
        path(basePath).nest {
            GET("/", handler::welcome)
        }
    }
}
