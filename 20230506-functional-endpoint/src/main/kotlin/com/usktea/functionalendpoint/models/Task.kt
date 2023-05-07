package com.usktea.functionalendpoint.models

import com.usktea.functionalendpoint.dtos.TaskDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Task(var title: String) {
    constructor(id: Long, title: String) : this(title) {
        this.id = id
    }

    @Id
    @GeneratedValue
    private var id: Long? = null

    fun toDto(): TaskDto {
        return TaskDto(id!!, title)
    }

}
