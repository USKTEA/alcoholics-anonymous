package com.usktea.todolist.dtos

data class TasksDto(private val tasks: List<TaskDto>) {
    fun getTasks(): List<TaskDto> = tasks
}

