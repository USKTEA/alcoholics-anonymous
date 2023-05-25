package com.usktea.security.config

import io.github.oshai.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain

private val log = KotlinLogging.logger {}

@Configuration
@EnableWebFlux
class WebConfig : WebFluxConfigurer {
    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/")
            .allowCredentials(false)
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .maxAge(0)

    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun corsFilter(): CorsWebFilter {
        val config = CorsConfiguration()
        config.allowCredentials = false
        config.addAllowedOriginPattern("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")

        val source = UrlBasedCorsConfigurationSource()

        source.registerCorsConfiguration("/", config)

        return CorsWebFilter(source)
    }
    
//    @Bean
//    fun authenticationFilter(): WebFilter {
//        return WebFilter { exchange: ServerWebExchange, chain: WebFilterChain ->
//            if (!exchange.request.headers.containsKey("Authorization")) {
//                exchange.response.statusCode = HttpStatus.UNAUTHORIZED
//
//                log.info("Request: ${exchange.request.method} ${exchange.request.uri}")
//                return@WebFilter Mono.empty()
//            }
//
//            chain.filter(exchange)
//        }
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    fun loggingFilter(): WebFilter {
        return WebFilter { exchange: ServerWebExchange, chain: WebFilterChain ->
            // Before request processing
            log.info("Request: ${exchange.request.method} ${exchange.request.uri}")

            // Continue processing
            chain.filter(exchange).doFinally {
                // After response processing
                log.info("Response: ${exchange.response.statusCode}")
            }
        }
    }
}
