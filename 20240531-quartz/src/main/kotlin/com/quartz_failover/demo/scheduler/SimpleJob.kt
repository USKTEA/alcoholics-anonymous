package com.quartz_failover.demo.scheduler

import com.quartz_failover.demo.cache.SampleCache
import com.quartz_failover.demo.entity.Sample
import com.quartz_failover.demo.repository.SimpleRepository
import org.apache.fury.Fury
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.redis.core.RedisTemplate


open class SimpleJob(
    private val simpleRepository: SimpleRepository,
    private val redisTemplate: RedisTemplate<String, SampleCache>,
    private val fury: Fury,
) : Job {

    override fun execute(context: JobExecutionContext) {
        var pageNumber = context.mergedJobDataMap.getInt("page")
        val pageSize = 1000

        try {
            var pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(Sort.Direction.ASC, "createdAt")
            )

            var samples = simpleRepository.findAll(pageable)

            while (samples.hasContent()) {
//                tries += 1
//
//                println(pageable.pageNumber)
//
//                if (tries % 10 == 0) {
//                    throw JobExecutionException("Error occurred while processing page $pageNumber")
//                }

                updateCache(samples.content)
                pageable = pageable.next()
                pageNumber = pageable.pageNumber
                samples = simpleRepository.findAll(pageable)
            }
        } catch (exception: Exception) {
            context.jobDetail.jobDataMap["page"] = pageNumber
            throw JobExecutionException("Error occurred while processing page $pageNumber", exception)
        }
    }

    private fun updateCache(samples: List<Sample>) {
        val caches = samples.map { SampleCache.from(it) }.associateBy { it.name }

        redisTemplate.executePipelined { connection ->
            caches.forEach { cache ->
                val key = redisTemplate.stringSerializer.serialize(cache.key)
                val value = fury.serialize(cache.value)

                connection.stringCommands().set(key!!, value!!)
                connection.keyCommands().expire(key, 100L)
            }

            null
        }
    }
}
