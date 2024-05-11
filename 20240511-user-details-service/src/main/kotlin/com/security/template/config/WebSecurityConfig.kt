package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Bean
    fun webSecurityFilterChain(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/", "/login", "/api/login").permitAll()
            auth.anyRequest().authenticated()
        }
            .addFilterBefore(
                customAuthenticateFilter(http, authenticationManager),
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        return builder.build()
    }

    @Bean
    fun customAuthenticateFilter(
        http: HttpSecurity,
        manager: AuthenticationManager,
    ): CustomUsernamePasswordAuthenticateFilter {
        val filter = CustomUsernamePasswordAuthenticateFilter(http)
        filter.setAuthenticationManager(manager)

        filter.setAuthenticationSuccessHandler { request, response, authentication ->
            response.sendRedirect("/hello")
        }

        filter.setAuthenticationFailureHandler { request, response, authentication ->
            response.sendRedirect("/login")
        }

        return filter
    }
}
