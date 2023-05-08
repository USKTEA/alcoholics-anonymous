package com.usktea.todolist.endpoints

import com.usktea.todolist.dtos.CreateTaskRequestDto
import com.usktea.todolist.dtos.TaskDto
import com.usktea.todolist.dtos.UpdateTaskRequestDto
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

    @Test
    fun create() {
        val createTaskRequestDto = CreateTaskRequestDto("밥 많이 먹기")

        given(taskService.create(createTaskRequestDto)).willReturn(TaskDto(1L, "밥 많이 먹기"))

        client.post()
            .uri("/tasks")
            .bodyValue(createTaskRequestDto)
            .exchange()
            .expectStatus()
            .isCreated
            .expectBody()
            .jsonPath("$.id")
            .exists()
    }

    @Test
    fun edit() {
        val id = 1L
        val title = "더 많이 먹기"
        val updateTaskRequestDto = UpdateTaskRequestDto(id, title)

        given(taskService.edit(id, title)).willReturn(TaskDto(id, title))

        client.patch()
            .uri("/tasks")
            .bodyValue(updateTaskRequestDto)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.id")
            .exists()
    }

    @Test
    fun delete() {
        val id = 1L

        given(taskService.delete(id)).willReturn(TaskDto(id, "밥 먹기"))

        client.delete()
            .uri("/tasks/$id")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.id")
            .exists()
    }

}
