package com.spa_rest.demo.controller

import com.spa_rest.demo.entity.Todo
import com.spa_rest.demo.repository.TodoRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val repository: TodoRepository
) {

    @GetMapping
    fun getTodos() = repository.findAll()

    @PostMapping
    fun createTodo(@RequestBody todo: Todo) = repository.save(todo)

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody updatedTodo: Todo): Todo {
        val todo = repository.findById(id).orElseThrow { RuntimeException("Todo not found") }
        todo.text = updatedTodo.text
        todo.completed = updatedTodo.completed
        return repository.save(todo)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long) = repository.deleteById(id)
}
