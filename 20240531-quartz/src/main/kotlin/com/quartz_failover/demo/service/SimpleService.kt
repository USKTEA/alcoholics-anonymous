package com.quartz_failover.demo.service

import com.quartz_failover.demo.cache.SampleCache
import com.quartz_failover.demo.entity.Sample
import com.quartz_failover.demo.repository.SimpleRepository
import java.time.OffsetDateTime
import java.util.UUID
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimpleService(
    private val simpleRepository: SimpleRepository,
    private val redisTemplate: RedisTemplate<String, SampleCache>
) {

    fun hello() {
        println(OffsetDateTime.now().second)
    }

    @Transactional
    fun createSamples() {
        val samples = mutableListOf<Sample>()
        for (i: Int in 1..100000) {
            samples.add(
                Sample(
                    id = UUID.randomUUID(),
                    name = "샘플$i",
                    number = i,
                    count = 0,
                    createdAt = OffsetDateTime.now().plusDays(i.toLong())
                )
            )
        }

        simpleRepository.saveAll(samples)
        println("끝")
    }

    fun check(): Int {
        val names = simpleRepository.findAll().map { it.name }.chunked(1000)

        var cahced = 0

        for (chunkced in names) {
            val got = redisTemplate.opsForValue().multiGet(chunkced) ?: emptyList()

            println(got[0])
            cahced += got.size
        }

        return cahced
    }
}
