package com.security.template.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/failed")
    fun login(): String {
        return "need login"
    }

    @GetMapping("/success")
    fun success(): String {
        return "success"
    }
}
