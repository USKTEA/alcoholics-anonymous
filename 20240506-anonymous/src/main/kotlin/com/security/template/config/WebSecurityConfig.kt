package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import kotlin.math.log

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
class WebSecurityConfig {

    @Bean
    fun webSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/anonymous").hasRole("GUEST")
            auth.requestMatchers("/logoutSuccess").hasRole("GUEST")
            auth.requestMatchers("/wow").permitAll()
            auth.anyRequest().authenticated()
        }.formLogin { form ->
            form.defaultSuccessUrl("/")
            form.permitAll()
        }.logout { logout ->
            logout.logoutSuccessHandler { request, response, auth ->
                response.sendRedirect("/logoutSuccess")
            }
        }.anonymous { anonymous ->
            anonymous.principal("guest")
            anonymous.authorities("ROLE_GUEST")
        }

        return http.build()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password("{noop}1111")
            .authorities("ROLE_USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
