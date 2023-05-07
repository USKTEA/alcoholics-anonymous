package com.usktea.todolist.endpoints

import com.usktea.todolist.dtos.TaskDto
import com.usktea.todolist.dtos.TasksDto
import com.usktea.todolist.services.TaskService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class TaskHandler(private val taskService: TaskService) {

    suspend fun list(request: ServerRequest): ServerResponse {
        val taskDtos = taskService.allTasks()

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(TasksDto(taskDtos))
    }

    suspend fun task(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val taskDto = taskService.getTask(id)

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(taskDto)
    }
}