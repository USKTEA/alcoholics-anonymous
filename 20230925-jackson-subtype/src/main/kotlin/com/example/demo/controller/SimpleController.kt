package com.example.demo.controller

import com.example.demo.dtos.ClovaRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class SimpleController {

    @PostMapping
    fun simple(
        @RequestBody body: ClovaRequest
    ) {
        println(body)
    }
}
