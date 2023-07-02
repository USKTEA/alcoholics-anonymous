package com.usktea.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/")
@RestController
class WelcomeController {

    @GetMapping
    fun welcome() : String {
        return "hello, world"
    }
}