package com.mongo_transaction.demo.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("TODO")
class Todo(
    @Id
    val id: Long,
    val task: String
)
