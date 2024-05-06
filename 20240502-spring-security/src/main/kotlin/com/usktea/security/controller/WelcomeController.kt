package com.usktea.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping("/")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/welcome")
    fun welcome(): String {
        return "<h2>welcome<h2>"
    }

    @GetMapping("/bye")
    fun bye(): String {
        return "bye"
    }
}
