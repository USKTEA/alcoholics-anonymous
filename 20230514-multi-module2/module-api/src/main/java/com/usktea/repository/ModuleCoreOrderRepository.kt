package com.usktea.repository

import com.usktea.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface ModuleCoreOrderRepository : JpaRepository<Order, Long>
