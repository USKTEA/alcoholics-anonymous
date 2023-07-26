package com.example.hibernatereactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HibernateReactiveApplication

fun main(args: Array<String>) {
	runApplication<HibernateReactiveApplication>(*args)
}
