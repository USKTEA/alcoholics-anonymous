package com.usktea.todolistreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodolistReactiveApplication

fun main(args: Array<String>) {
	runApplication<TodolistReactiveApplication>(*args)
}
