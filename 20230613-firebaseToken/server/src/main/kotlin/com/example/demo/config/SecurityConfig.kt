package com.example.demo.config

import com.example.demo.clients.FirebaseClient
import com.example.demo.properties.FirebaseProperties
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val firebaseClient: FirebaseClient
) : WebMvcConfigurer {
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOriginPatterns("*")
//            .allowedMethods("*")
//            .allowedHeaders("*")
//            .maxAge(0)
//            .allowCredentials(true)
//    }

    @Bean
    fun corsFilter(): CorsFilter {
        val configuration = CorsConfiguration().apply {
            allowCredentials = false
            addAllowedOrigin("*")
            addAllowedHeader("*")
            addAllowedMethod("*")
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }

        return CorsFilter(source)
    }

    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        http.addFilterBefore(FriendlyFilter(authenticationManager()), LogoutFilter::class.java)
            .csrf { disable() }
            .cors { disable() }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests.anyRequest().authenticated()
            }

        return http.build()
    }

//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("*")
//        configuration.allowedMethods = listOf("*")
//        configuration.allowedHeaders = listOf("*")
//        configuration.allowCredentials = false
//
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(CustomAuthenticationProvider(firebaseClient))
    }
}



