package com.usktea.jwt.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping
    fun hello(): String {
        return "hello, world"
    }
}
