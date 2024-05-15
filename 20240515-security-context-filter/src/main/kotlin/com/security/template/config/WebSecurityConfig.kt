package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity, manager: AuthenticationManager): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { auth ->
            auth.requestMatchers(
                "/login", "/api/login").permitAll()
            auth.anyRequest().authenticated()
        }.addFilterBefore(
            customAuthenticateFilter(httpSecurity, manager), UsernamePasswordAuthenticationFilter::class.java
        )

        return httpSecurity.build()
    }

    @Bean
    fun customAuthenticateFilter(httpSecurity: HttpSecurity, manager: AuthenticationManager): CustomAuthenticateFilter {
        return CustomAuthenticateFilter(httpSecurity, manager)
    }

    @Bean
    fun authenticationManager(httpSecurity: HttpSecurity): AuthenticationManager {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder::class.java).build()
    }
}
