package com.example.demo.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    //TODO CorsFilter

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(CustomAuthenticationProvider())
    }

    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        http.addFilterBefore(FriendlyFilter(authenticationManager()), LogoutFilter::class.java)
            .csrf { disable() }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/info").authenticated()
                    .anyRequest().permitAll()
            }

        return http.build()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(CustomAuthenticationProvider())
    }
}