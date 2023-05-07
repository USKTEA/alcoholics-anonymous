package com.usktea.todolist.services

import com.usktea.todolist.models.Task
import com.usktea.todolist.repositories.TaskRepository
import jakarta.persistence.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

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
    fun allTasks() {
        given(taskRepository.findAll()).willReturn(listOf(Task(1L, "밥먹기")))

        val tasks = taskService.allTasks()

        assertThat(tasks).isNotNull
    }

    @Test
    fun whenHaveOneTask() {
        given(taskRepository.findAll()).willReturn(listOf(Task(1L, "밥먹기")))

        val tasks = taskService.allTasks()

        assertThat(tasks).hasSize(1)
    }

    @Test
    fun getTask() {
        val id = 1L
        given(taskRepository.findById(id)).willReturn(Optional.of(Task(id, "밥먹기")))

        val task = taskService.getTask(id)

        assertThat(task.getId()).isEqualTo(id)
    }

    @Test
    fun whenTaskNotExists() {
        val id = 9_999_999L

        given(taskRepository.findById(id)).willReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { taskService.getTask(id) }
    }
}
