package com.usktea.circuit.demo.controller

import com.usktea.circuit.demo.service.SimpleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SimpleController(
    private val simpleService: SimpleService
) {

    @GetMapping
    fun test(
        @RequestParam failurePercent: Int,
        @RequestParam delay: Int,
    ): List<String> {
        return simpleService.simple(failurePercent, delay)
    }
}
