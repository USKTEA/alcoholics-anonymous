package com.mongo_transaction.demo.repository

import com.mongo_transaction.demo.document.Todo
import org.springframework.data.mongodb.repository.MongoRepository

interface TodoRepository : MongoRepository<Todo, Long>{
    fun findByTask(task: String): Todo?
}
