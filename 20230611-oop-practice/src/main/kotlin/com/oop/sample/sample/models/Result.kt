package com.oop.sample.sample.models

import com.oop.sample.sample.dtos.ResultDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue

@Entity
class Result(
    private val value: Int
) {
    @GeneratedValue
    private var id: Long? = null
    private var active = true

    constructor(id: Long, value: Int) : this(value) {
        this.id = id
    }

    fun toDto(): ResultDto {
        return ResultDto(id!!, value)
    }
}
