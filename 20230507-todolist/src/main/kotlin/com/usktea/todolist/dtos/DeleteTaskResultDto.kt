package com.usktea.todolist.dtos

data class DeleteTaskResultDto(private val id: Long) {
    fun getId(): Long {
        return id
    }
}
