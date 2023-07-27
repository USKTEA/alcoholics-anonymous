package com.usktea.todolistreactive.entity

data class CreateBookSpec(
    val name: String,
    val meta: BookMeta
)

data class FindBookSpec(
    val name: String,
    val publisher: String? = null
)

data class UpdateBookSpec(
    val findBookSpec: FindBookSpec,
    val name: String
)

data class NameIsbnVO(
    val name: String,
    val isbn10: String,
    val isbn13: String
)
