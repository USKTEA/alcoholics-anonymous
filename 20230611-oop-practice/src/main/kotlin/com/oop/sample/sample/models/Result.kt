package com.oop.sample.sample.models

import com.oop.sample.sample.dtos.ResultDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "calculate_result")
class Result(
    private val calculatedValue: Int
) {
    @Id
    @GeneratedValue
    private var id: Long? = null
    private var active = true

    constructor(id: Long, value: Int) : this(value) {
        this.id = id
    }

    fun toDto(): ResultDto {
        return ResultDto(id!!, calculatedValue)
    }
}
