package com.usktea.todolist.models

import com.usktea.todolist.dtos.TaskDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Task(private var title: String) {
    constructor(id: Long, title: String) : this(title) {
        this.id = id
    }

    @Id
    @GeneratedValue
    private var id: Long? = null

    fun toDto() : TaskDto {
        return TaskDto(id!!, title)
    }
}
