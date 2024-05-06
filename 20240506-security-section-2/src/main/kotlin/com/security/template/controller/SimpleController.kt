package com.security.template.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/welcome")
    fun welcome(): String {
        return "welcome"
    }

    @GetMapping("not-welcome")
    fun notWelcome(): String {
        return "notWelcome"
    }

    @PreAuthorize("hasRole('ROLE_GUEST')")
    @GetMapping("anonymous")
    fun anonymous(): String {
        return "hello anony"
    }

    @GetMapping("/bye")
    fun bye(): String {
        return "bye"
    }
}
