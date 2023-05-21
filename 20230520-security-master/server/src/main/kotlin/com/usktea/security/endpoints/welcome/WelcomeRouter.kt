package com.usktea.security.endpoints.welcome

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WelcomeRouter {

    @Bean
    fun welcomeRoute(handler: WelcomeHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/", handler::welcome)
        }
    }
}