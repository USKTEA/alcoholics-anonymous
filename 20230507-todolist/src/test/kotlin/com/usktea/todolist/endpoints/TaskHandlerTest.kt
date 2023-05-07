package com.usktea.todolist.endpoints

import com.usktea.todolist.dtos.TaskDto
import com.usktea.todolist.services.TaskService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(TaskRouter::class, TaskHandler::class)
internal class TaskHandlerTest {
    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var taskService: TaskService

    @Test
    fun list() {
        given(taskService.allTasks()).willReturn(listOf(TaskDto(1L, "밥먹기")))

        client.get()
            .uri("/tasks")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.tasks")
            .exists()
    }

    @Test
    fun task() {
        val id = 1L

        given(taskService.getTask(id)).willReturn(TaskDto(id, "밥먹기"))

        client.get()
            .uri("/tasks/$id")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id")
            .exists()
    }
}
