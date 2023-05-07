package com.usktea.functionalendpoint.welcome

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

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
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<String>().consumeWith { response ->
                Assertions.assertThat(response.responseBody).contains("Hello, This is Todo List")
            }
    }
}
