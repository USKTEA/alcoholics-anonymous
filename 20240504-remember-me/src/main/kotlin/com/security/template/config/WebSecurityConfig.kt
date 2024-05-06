package com.security.template.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { it.anyRequest().authenticated() }
            .formLogin(Customizer.withDefaults())
            .rememberMe {
                it.authenticationSuccessHandler { request, response, auth ->
                    response.sendRedirect("/remember")
                }
                it.userDetailsService(userDetailService())
                it.tokenValiditySeconds(3600)
                it.rememberMeParameter("remember")
                it.rememberMeCookieName("remember")
                it.key("security")
                it.useSecureCookie(true)
                it.alwaysRemember(true)
            }

        return httpSecurity.build()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        val user = User
            .builder()
            .username("user")
            .password("{noop}1111")
            .authorities("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
