package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Bean
    fun webSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationProvider = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        val manager = authenticationProvider.build()

        http.authorizeHttpRequests {auth ->
            auth.anyRequest().authenticated()
        }.authenticationManager(manager)

        return http.build()
    }

    fun authenticationFiler():
    @Bean
    fun userDetailService(): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password("{noop}1111")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
