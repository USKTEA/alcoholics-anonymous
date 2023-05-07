package com.usktea.todolist.services

import com.usktea.todolist.dtos.TaskDto
import com.usktea.todolist.models.Task
import com.usktea.todolist.repositories.TaskRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun allTasks(): List<TaskDto> {
        val tasks = taskRepository.findAll()

        return tasks.map { task -> task.toDto() }
    }

    fun getTask(id: Long): TaskDto {
        val task: Task = taskRepository.findById(id).orElseThrow { EntityNotFoundException() }

        return task.toDto()
    }
}
