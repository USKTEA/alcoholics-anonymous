package com.usktea.dtos

import com.usktea.entity.Customer

class CustomerDto(
    val nickname: String,
    val address: String
) {
    fun toEntity() =
        Customer(
            nickname = this.nickname,
            address = this.address
        )
}
