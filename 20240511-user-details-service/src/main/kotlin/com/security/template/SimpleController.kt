package com.security.template

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/hello")
    fun success(authentication: Authentication): Authentication {
        return authentication
    }
}
