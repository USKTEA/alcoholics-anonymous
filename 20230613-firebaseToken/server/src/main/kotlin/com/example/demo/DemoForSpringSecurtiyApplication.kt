package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication
@EnableMethodSecurity
class DemoForSpringSecurtiyApplication

fun main(args: Array<String>) {
	runApplication<DemoForSpringSecurtiyApplication>(*args)
}
