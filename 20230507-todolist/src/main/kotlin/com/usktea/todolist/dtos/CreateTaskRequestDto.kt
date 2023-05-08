package com.usktea.todolist.dtos

data class CreateTaskRequestDto(private val title: String) {
    fun getTitle(): String {
        return title
    }
}
