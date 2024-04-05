package com.example.demo.controller

import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import java.net.URI
import java.nio.charset.Charset

@RestController
@RequestMapping("/")
class TestController(
    val environment: Environment
) {

    @PostMapping
    fun send() {
        val restTemplate = RestTemplate()
        val url = "http://localhost:9999/test"

        // GET 요청 보내고 응답 받기
        val responseEntity = restTemplate.postForEntity(url, Test("eng", "코코코코코ㅗ코"),Test::class.java)

        val env = environment.getProperty("server.servlet.encoding.charset")
        println(env)

        println(responseEntity.body)
    }
}

data class Test(
    val english: String,
    val korean: String,
)
