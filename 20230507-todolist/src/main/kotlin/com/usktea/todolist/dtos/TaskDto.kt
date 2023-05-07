package com.usktea.todolist.dtos

data class TaskDto(private val id: Long, private val title: String) {
    fun getId(): Long {
        return id
    }

    fun getTitle(): String {
        return title
    }
}
