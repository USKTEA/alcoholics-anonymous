package com.oauth.resource_server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class ResourceServerConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.securityMatcher("/articles/**")
            .authorizeHttpRequests { auth -> auth.anyRequest()
                .hasAnyAuthority("SCOPE_articles.read")}
            .oauth2ResourceServer { oauth2 -> oauth2.jwt(Customizer.withDefaults()) }

        return httpSecurity.build()
    }
}
