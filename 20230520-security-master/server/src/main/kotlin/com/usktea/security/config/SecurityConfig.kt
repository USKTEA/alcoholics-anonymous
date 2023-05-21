package com.usktea.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    open fun webHttpSecurity(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http {
            cors { disable() }
            formLogin { disable() }

        }
    }
}
