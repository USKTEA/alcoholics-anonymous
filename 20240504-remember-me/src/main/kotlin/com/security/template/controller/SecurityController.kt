package com.security.template.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {

    @GetMapping("/")
    fun welcome() : String {
        return "welcome"
    }

    @GetMapping("/remember")
    fun remember(): String {
        return "remember"
    }
}
