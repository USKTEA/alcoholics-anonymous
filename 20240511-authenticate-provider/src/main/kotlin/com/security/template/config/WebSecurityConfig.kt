package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun webSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticateManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        val manager = authenticateManagerBuilder.build()

        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/", "/api/login").permitAll()
            auth.anyRequest().authenticated()
        }.addFilterBefore(customAuthenticateFilter(http, manager), UsernamePasswordAuthenticationFilter::class.java)
            .authenticationManager(manager)

        return http.build()
    }

    fun customAuthenticateFilter(http: HttpSecurity, manager: AuthenticationManager): CustomAuthenticateFilter {
        val filter = CustomAuthenticateFilter(http)
        filter.setAuthenticationManager(manager)

        return filter
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password("{noop}1111")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun customAuthenticateProvider(): CustomAuthenticateProvider {
        return CustomAuthenticateProvider()
    }
}

