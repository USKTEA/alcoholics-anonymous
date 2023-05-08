package com.usktea.todolist.services

import com.usktea.todolist.dtos.CreateTaskRequestDto
import com.usktea.todolist.dtos.TaskDto
import com.usktea.todolist.models.Task
import com.usktea.todolist.repositories.TaskRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class TaskService(private val taskRepository: TaskRepository) {

    fun allTasks(): List<TaskDto> {
        val tasks = taskRepository.findAll()

        return tasks.map { task -> task.toDto() }
    }

    fun getTask(id: Long): TaskDto {
        val task: Task = taskRepository.findById(id).orElseThrow { EntityNotFoundException() }

        return task.toDto()
    }

    fun create(createTaskRequestDto: CreateTaskRequestDto): TaskDto {
        val saved = taskRepository.save(Task(createTaskRequestDto.getTitle()))

        return saved.toDto()
    }

    fun edit(id: Long, title: String): TaskDto {
        val task = taskRepository.findById(id).orElseThrow { EntityNotFoundException() }

        task.edit(title)

        return task.toDto()
    }

    fun delete(id: Long): TaskDto {
        val task = taskRepository.findById(id).orElseThrow { EntityNotFoundException() }

        taskRepository.delete(task)

        return task.toDto()
    }
}
