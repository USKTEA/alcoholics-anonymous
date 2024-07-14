package com.mongo_transaction.demo.controller

import com.mongo_transaction.demo.service.TodoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
) {

    @PostMapping
    fun createBread(
        @RequestBody request: CreateTodoRequest
    ) : String {
        return todoService.createTodo(request)
    }
}

data class CreateTodoRequest(
    val task: String
)

data class UpdateTodoRequest(
    val task: String,
)
