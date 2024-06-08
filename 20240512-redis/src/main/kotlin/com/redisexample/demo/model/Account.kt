package com.redisexample.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 1L)
data class Account(
    @Id
    val name: String,
)
