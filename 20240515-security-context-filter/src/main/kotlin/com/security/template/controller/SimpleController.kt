package com.security.template.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/api/login")
    fun login(authentication: Authentication) : Authentication {
        return authentication
    }

    @GetMapping("/")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}
