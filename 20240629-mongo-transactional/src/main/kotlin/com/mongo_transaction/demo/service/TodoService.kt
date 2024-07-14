package com.mongo_transaction.demo.service

import com.mongo_transaction.demo.controller.CreateTodoRequest
import com.mongo_transaction.demo.controller.UpdateTodoRequest
import com.mongo_transaction.demo.document.Todo
import com.mongo_transaction.demo.repository.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {
    fun createTodo(request: CreateTodoRequest): String {
        return todoRepository.save(
            Todo(
                id = 1L,
                task = request.task
            )
        ).task
    }

    @Transactional
    fun updateTodo(request: UpdateTodoRequest): String {
        return requireNotNull(todoRepository.findByTask(request.task)).task
    }
}
