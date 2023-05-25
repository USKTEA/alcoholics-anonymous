package com.usktea.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    open fun webHttpSecurity(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http {
            cors { disable() }
            authorizeExchange {
                authorize(anyExchange, authenticated)
            }
            formLogin { disable() }
        }
    }
//
    @Bean
    open fun apiHttpSecurity(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http {
            csrf { disable() }
            authorizeExchange {
                authorize("/admin/v1/media/**", permitAll)
                authorize(anyExchange, authenticated)
            }
        }
    }
}
