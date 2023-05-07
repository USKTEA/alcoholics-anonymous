package com.usktea.functionalendpoint.welcome

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WelcomeRouter(handler: WelcomeHandler) {

    @Bean
    fun welcomeRoutes(handler: WelcomeHandler) = coRouter {
        GET("/", handler::welcome)
    }
}