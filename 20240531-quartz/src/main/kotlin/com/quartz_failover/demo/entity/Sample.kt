package com.quartz_failover.demo.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.OffsetDateTime
import java.util.UUID

@Entity
class Sample(
    @Id
    val id: UUID,
    val name: String,
    val number: Int,
    var count: Int,
    val createdAt: OffsetDateTime,
)
