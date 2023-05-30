package com.example.demo.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @RequestMapping("/")
    fun home(): String {
        return "home"
    }


    @RequestMapping("/users")
    fun users(): String {
        return "users"
    }
}