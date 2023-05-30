package com.example.demo.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @RequestMapping("/")
    fun home(): String {
        return "home"
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/users")
    fun users(): String {
        return "users"
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    fun admin(): String {
        return "admin"
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/info")
    fun info(): String {
        return "info"
    }
}