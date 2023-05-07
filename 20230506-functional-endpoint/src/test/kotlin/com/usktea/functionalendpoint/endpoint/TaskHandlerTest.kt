package com.usktea.functionalendpoint.endpoint

import com.usktea.functionalendpoint.dtos.TaskDto
import com.usktea.functionalendpoint.endpoint.TaskHandler
import com.usktea.functionalendpoint.endpoint.TaskRouter
import com.usktea.functionalendpoint.services.TaskService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(TaskRouter::class, TaskHandler::class)
@ActiveProfiles("test")
internal class TaskHandlerTest {
    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var taskService: TaskService

    @Test
    fun lists() {
        given(taskService.lists()).willReturn(listOf(TaskDto(1L, "밥먹기")))

        client.get()
            .uri("/tasks")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.tasks").exists()
    }
}
