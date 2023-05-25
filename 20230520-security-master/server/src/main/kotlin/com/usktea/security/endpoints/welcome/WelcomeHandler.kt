package com.usktea.security.endpoints.welcome

import io.github.oshai.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

private val log = KotlinLogging.logger {}

@Component
class WelcomeHandler {

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    suspend fun welcome(request: ServerRequest): ServerResponse {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait("welcome")
    }
}
