package com.usktea.demo.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig : WebMvcConfigurer {
    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { disable() }
            .cors { disable() }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .anyRequest().permitAll()
            }

        return http.build()
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .allowedHeaders("*")
    }
}
