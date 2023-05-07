package com.usktea.functionalendpoint.welcome

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class WelcomeHandler {
    suspend fun welcome(request: ServerRequest): ServerResponse {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait("Hello, This is Todo List")
    }
}