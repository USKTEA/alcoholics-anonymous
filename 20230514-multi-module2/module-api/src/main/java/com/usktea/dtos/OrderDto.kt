package com.usktea.dtos

import com.usktea.entity.Customer
import com.usktea.entity.Order

data class OrderDto(
    val orderStoreName: String,
    val orderStoreAddress: String,
    val orderItem: String,
    val orderPrice: Int,
    val customer: Customer,
) {
    fun toEntity() = Order(
        orderStoreName = this.orderStoreName,
        orderStoreAddress = this.orderStoreAddress,
        orderItem = this.orderItem,
        orderPrice = this.orderPrice,
        customer = this.customer
    )
}
