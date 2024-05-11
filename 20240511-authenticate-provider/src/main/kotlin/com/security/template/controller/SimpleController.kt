package com.security.template.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/api/login")
    fun welcome(
        @RequestParam username: String,
        @RequestParam password: String,
    ): String {
        return "dddd"
    }
}
