package com.discord.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {
    @Bean
    fun restClient(): RestClient {
        return RestClient.builder()
            .baseUrl("디스코드 웹훅 URL")
            .build()
    }
}
