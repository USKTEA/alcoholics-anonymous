package com.usktea.mongo_test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongoTestApplication

fun main(args: Array<String>) {
	runApplication<MongoTestApplication>(*args)
}
