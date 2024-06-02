package com.quartz_failover.demo.config

import com.quartz_failover.demo.cache.SampleCache
import org.apache.fury.Fury
import org.apache.fury.config.Language
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FuryConfig {
    @Bean
    fun fury(): Fury {
        val fury = Fury.builder()
            .withRefTracking(false)
            .withLanguage(Language.JAVA)
            .build()

        fury.register(SampleCache::class.java)

        return fury
    }
}
