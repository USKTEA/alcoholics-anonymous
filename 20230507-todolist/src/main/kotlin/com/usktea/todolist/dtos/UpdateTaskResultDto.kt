package com.usktea.todolist.dtos

data class UpdateTaskResultDto(private val id: Long) {
    fun getId(): Long {
        return id
    }
}
