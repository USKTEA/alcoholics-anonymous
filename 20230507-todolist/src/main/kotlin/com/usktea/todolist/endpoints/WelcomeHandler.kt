package com.usktea.todolist.endpoints

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class WelcomeHandler {
    suspend fun welcome(request: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait("welcome")
    }
}
