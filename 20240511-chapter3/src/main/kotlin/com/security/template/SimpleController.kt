package com.security.template

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/hello")
    fun hello(authentication: Authentication): Authentication {
        return authentication
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/")
    fun welcome(): String {
        return "welcome"
    }
}
