package com.usktea.todolist.endpoints

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(WelcomeRouter::class, WelcomeHandler::class)
internal class WelcomeHandlerTest {
    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun welcome() {
        client.get()
            .uri("/")
            .exchange()
            .expectStatus().isOk
    }
}
