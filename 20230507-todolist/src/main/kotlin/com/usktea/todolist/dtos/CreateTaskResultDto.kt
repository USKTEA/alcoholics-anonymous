package com.usktea.todolist.dtos

data class CreateTaskResultDto(private val id: Long) {
    fun getId(): Long {
        return id
    }
}
