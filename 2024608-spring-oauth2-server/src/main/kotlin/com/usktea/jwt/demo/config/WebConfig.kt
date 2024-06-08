package com.usktea.jwt.demo.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class WebConfig {
    @Bean
    fun webSecurityConfig(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/sessions").permitAll()
            auth.anyRequest().authenticated()
        }
            .cors { disable() }
            .csrf { disable() }
            .formLogin { disable() }
            .addFilterBefore(
                authenticationFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }

    @Bean
    fun authenticationFilter(authenticationManager: AuthenticationManager): JwtAuthenticateFilter {
        return JwtAuthenticateFilter(authenticationManager)
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val manager = http.getSharedObject(AuthenticationManagerBuilder::class.java)

        return manager.build()
    }
}
