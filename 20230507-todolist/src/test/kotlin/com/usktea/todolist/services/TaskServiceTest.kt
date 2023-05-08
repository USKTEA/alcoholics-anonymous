package com.usktea.todolist.services

import com.usktea.todolist.dtos.CreateTaskRequestDto
import com.usktea.todolist.models.Task
import com.usktea.todolist.repositories.TaskRepository
import jakarta.persistence.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
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

    @Test
    fun create() {
        val title = "밥 많이 먹기"
        val createTaskRequestDto = CreateTaskRequestDto(title)

        given(taskRepository.save(any())).willReturn(Task(1L, title))

        val created = taskService.create(createTaskRequestDto)

        assertThat(created.getTitle()).isEqualTo(title)
        Mockito.verify(taskRepository).save(any())
    }

    @Test
    fun whenEditEntityNotFound() {
        val id = 9_999_999L
        val title = "밥 더 많이 먹기"

        given(taskRepository.findById(id)).willReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { taskService.edit(id, title) }
    }

    @Test
    fun whenEditSuccess() {
        val id = 1L
        val title = "밥 더 많이 많이 먹기"

        given(taskRepository.findById(id)).willReturn(Optional.of(Task(id, "밥 많이 먹기")))

        val edited = taskService.edit(id, title)

        assertThat(edited.getId()).isEqualTo(id)
        assertThat(edited.getTitle()).isEqualTo(title)
    }

    @Test
    fun whenDeleteFailed() {
        val id = 9_999_999L

        given(taskRepository.findById(id)).willReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { taskService.delete(id) }
    }

    @Test
    fun whenDeleteSuccess() {
        val id = 1L

        given(taskRepository.findById(id)).willReturn(Optional.of(Task(1L, "밥 먹기")))

        val deleted = taskService.delete(id)

        assertThat(deleted.getId()).isEqualTo(id)
        verify(taskRepository).delete(any())
    }
}
