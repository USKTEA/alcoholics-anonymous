package com.usktea.security.endpoints.welcome

import io.github.oshai.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

private val log = KotlinLogging.logger {}

@Component
class WelcomeHandler {

    suspend fun welcome(request: ServerRequest): ServerResponse {
        val cookies = request.cookies()

        val cookie = cookies?.get("cookie") ?: null

        log.info("${cookie.toString()}")

        val responseCookie = ResponseCookie.from("cookie", "cookievValue")
            .httpOnly(true)
            .path("*")
            .build()

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .cookie(responseCookie)
            .bodyValueAndAwait("welcome")
    }
}
