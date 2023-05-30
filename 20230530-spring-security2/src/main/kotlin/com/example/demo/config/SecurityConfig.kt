package com.example.demo.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {
    //TODO CorsFilter

    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        http.addFilterBefore(FriendlyFilter(), LogoutFilter::class.java)
            .csrf { disable() }
            .authorizeHttpRequests { authorizeRequests ->
            authorizeRequests
                .requestMatchers("/users**").authenticated()
                .anyRequest().permitAll()
        }

        return http.build()
    }
}