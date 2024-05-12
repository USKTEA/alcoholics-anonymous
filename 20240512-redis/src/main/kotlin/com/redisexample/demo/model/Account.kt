package com.redisexample.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(timeToLive = 10L)
data class Account(
    @Id
    val id: Long,

    @Indexed
    val name: String,
)
