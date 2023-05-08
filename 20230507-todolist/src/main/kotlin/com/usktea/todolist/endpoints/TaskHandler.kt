package com.usktea.todolist.endpoints

import com.usktea.todolist.dtos.*
import com.usktea.todolist.services.TaskService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI

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

    suspend fun create(request: ServerRequest): ServerResponse {
        val createTaskRequestDto = request.awaitBody<CreateTaskRequestDto>()
        val createdId: Long = taskService.create(createTaskRequestDto).getId()
        val uri = URI("/tasks/${createdId}")

        return ServerResponse
            .created(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(CreateTaskResultDto(createdId))
    }

    suspend fun edit(request: ServerRequest): ServerResponse {
        val (id, title) = getValues(request.awaitBody<UpdateTaskRequestDto>())
        val editedId: Long = taskService.edit(id, title).getId()

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(UpdateTaskResultDto(editedId))
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val deletedId = taskService.delete(id).getId()

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(DeleteTaskResultDto(1L))
    }

    private fun getValues(awaitBody: UpdateTaskRequestDto): Result {
        return Result(awaitBody.getId(), awaitBody.getTitle())
    }
}

data class Result(val id: Long, val title: String)
