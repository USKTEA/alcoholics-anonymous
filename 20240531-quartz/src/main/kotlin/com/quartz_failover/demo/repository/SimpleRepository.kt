package com.quartz_failover.demo.repository

import com.quartz_failover.demo.entity.Sample
import java.awt.print.Pageable
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface SimpleRepository : JpaRepository<Sample, UUID>
