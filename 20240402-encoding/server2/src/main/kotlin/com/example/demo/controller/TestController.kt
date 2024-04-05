package com.example.demo.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerRequest.Headers
import java.net.http.HttpHeaders
import java.nio.charset.Charset

@RestController
@RequestMapping("/test")
class TestController {

    @PostMapping
    fun test(
        @RequestBody request: Test
    ) : Test {
        println(Charset.defaultCharset())
        println(request)
        return request
    }
}

data class Test(
    val english: String,
    val korean: String,
)
