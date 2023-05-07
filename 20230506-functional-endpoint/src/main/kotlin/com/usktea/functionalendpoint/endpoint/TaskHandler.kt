package com.usktea.functionalendpoint.endpoint

import com.usktea.functionalendpoint.dtos.TaskDto
import com.usktea.functionalendpoint.dtos.TaskDtos
import com.usktea.functionalendpoint.services.TaskService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class TaskHandler(private val taskService: TaskService) {

    suspend fun lists(request: ServerRequest): ServerResponse {
        val tasks: List<TaskDto> = taskService.lists()

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(TaskDtos(tasks))
    }
}
