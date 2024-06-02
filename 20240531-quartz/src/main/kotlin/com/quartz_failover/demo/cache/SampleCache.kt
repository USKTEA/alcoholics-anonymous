package com.quartz_failover.demo.cache

import com.quartz_failover.demo.entity.Sample

data class SampleCache (
    val name: String,
    val number: Int,
    val count: Int,
) {
    companion object {
        fun from(it: Sample): SampleCache {
            return SampleCache(
                name = it.name,
                number = it.number,
                count = it.count,
            )
        }
    }
}
