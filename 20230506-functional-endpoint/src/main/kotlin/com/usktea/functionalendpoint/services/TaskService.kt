package com.usktea.functionalendpoint.services

import com.usktea.functionalendpoint.dtos.TaskDto
import com.usktea.functionalendpoint.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskService(private val taskRepository: TaskRepository) {

    @Transactional
    fun lists(): List<TaskDto> {
        val tasks = taskRepository.findAll()

        return tasks.map { task -> task.toDto() }
    }

}
