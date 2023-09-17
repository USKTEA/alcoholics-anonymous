package com.usktea.demo.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/no")
class NotWelcomeController {

    @RequestMapping
    fun notWelcome(): String {
        return "notWelcome"
    }
}
