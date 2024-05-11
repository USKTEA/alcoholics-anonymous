package com.security.template.config

import com.security.template.filter.CustomAuthenticateFilter
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
            auth.requestMatchers("/", "/api/login", "/login")
            auth.anyRequest().permitAll()
        }
            .addFilterBefore(
                customAuthenticateFilter(http, authenticationManager),
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java).build()
    }

    @Bean
    fun customAuthenticateFilter(
        http: HttpSecurity,
        authenticationManager: AuthenticationManager
    ): CustomAuthenticateFilter {
        return CustomAuthenticateFilter(http, authenticationManager).also {
            it.setAuthenticationSuccessHandler { request, response, authentication ->
                response.sendRedirect("/hello")
            }
            it.setAuthenticationFailureHandler { request, response, authentication ->
                response.sendRedirect("/login")
            }
        }
    }
}
