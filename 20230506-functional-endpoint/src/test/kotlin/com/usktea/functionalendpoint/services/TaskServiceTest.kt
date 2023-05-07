package com.usktea.functionalendpoint.services

import com.usktea.functionalendpoint.models.Task
import com.usktea.functionalendpoint.repository.TaskRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
internal class TaskServiceTest {
    @MockBean
    private lateinit var taskRepository: TaskRepository
    private lateinit var taskService: TaskService

    @BeforeEach
    fun setup() {
        taskRepository = mock(TaskRepository::class.java)
        taskService = TaskService(taskRepository)
    }

    @Test
    fun lists() {
        val task = Task(1L, "밥먹기")

        given(taskRepository.findAll()).willReturn(listOf(task))

        val tasks = taskService.lists()

        assertThat(tasks).hasSize(1)
        assertThat(tasks).first().isEqualTo(task.toDto())
    }
}
