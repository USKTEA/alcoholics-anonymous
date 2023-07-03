package com.usktea.demo.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Payment(
    val price: Long,
    val amount: Long,
    val productName: String,
) {
    @Id
    @GeneratedValue
    private var id: Long? = null
}