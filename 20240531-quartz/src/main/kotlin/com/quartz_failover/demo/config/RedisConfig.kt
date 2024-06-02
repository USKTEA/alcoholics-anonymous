package com.quartz_failover.demo.config

import com.quartz_failover.demo.cache.SampleCache
import org.apache.fury.Fury
import org.redisson.spring.data.connection.RedissonConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return RedissonConnectionFactory()
    }

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        fury: Fury
    ): RedisTemplate<String, SampleCache> {
        val template = RedisTemplate<String, SampleCache>()
        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.valueSerializer = FuryRedisSerializer<SampleCache>(fury)

        return template
    }
}

class FuryRedisSerializer<T>(
    private val fury: Fury
) : RedisSerializer<T> {
    override fun serialize(t: T?): ByteArray? {
        return fury.serialize(t)
    }

    override fun deserialize(bytes: ByteArray?): T? {
        return fury.deserialize(bytes) as T
    }
}
