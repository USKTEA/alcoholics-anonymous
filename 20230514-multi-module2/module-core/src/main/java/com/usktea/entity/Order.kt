package com.usktea.entity

import com.usktea.entity.base.BaseEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,

    @NotNull
    @Column
    val orderUUID: String = UUID.randomUUID().toString(),

    @NotNull
    @Column
    val orderStoreName: String,

    @NotNull
    @Column
    val orderStoreAddress: String,

    @NotNull
    @Column
    val orderItem: String,

    @NotNull
    @Column
    val orderPrice: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    val customer: Customer,
) : BaseEntity() {
}