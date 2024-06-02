package com.quartz_failover.demo.controller

import com.quartz_failover.demo.cache.SampleCache
import com.quartz_failover.demo.service.SimpleService
import org.apache.fury.Fury
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SimpleController(
    private val simpleService: SimpleService,
    private val fury: Fury
) {

    @GetMapping
    fun hello(
        @RequestParam number: Int
    ): String {
        return "hello-world"
    }

    @PostMapping
    fun createSamples() : String {
        simpleService.createSamples()
        return "ok"
    }

    @GetMapping("/checks")
    fun check(): Int {
        return simpleService.check()
    }
}
