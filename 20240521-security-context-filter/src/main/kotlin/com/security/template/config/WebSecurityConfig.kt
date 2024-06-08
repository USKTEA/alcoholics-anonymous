package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { auth ->
            auth.requestMatchers("/failed", "/api/login").permitAll()
            auth.anyRequest().authenticated()
        }.addFilterBefore(customAuthenticateFilter(httpSecurity, authenticationManager), UsernamePasswordAuthenticationFilter::class.java)

        return httpSecurity.build()
    }

    @Bean
    fun customAuthenticateFilter(httpSecurity: HttpSecurity, authenticationManager: AuthenticationManager): CustomAuthenticateFilter {
        return CustomAuthenticateFilter(httpSecurity, authenticationManager)
    }

    @Bean
    fun authenticationManager(httpSecurity: HttpSecurity): AuthenticationManager {
        val manager = httpSecurity.getSharedObject(AuthenticationManagerBuilder::class.java)
        return manager.build()
    }
}
