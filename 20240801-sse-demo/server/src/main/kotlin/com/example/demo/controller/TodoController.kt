package com.example.demo.controller

import com.example.demo.service.SseService
import java.util.Collections
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodoController(
    private val sseService: SseService
) {
    private val taskRepository = Collections.synchronizedList(
        mutableListOf<Task>()
    )

    @GetMapping
    fun getTodos(): List<Task> {
        return taskRepository
    }

    @PostMapping
    fun addTask(
        @RequestParam id: String,
        @RequestBody title: String
    ): String {
        val task = Task(taskRepository.size + 1, title)

        taskRepository.add(
            task
        )

        sseService.publish(id, task)

        return "ok"
    }

    data class Task(
        val id: Int,
        val title: String
    )
}

