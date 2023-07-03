package com.usktea.demo.repositories

import com.usktea.demo.models.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository: JpaRepository<Payment, Long> {
}