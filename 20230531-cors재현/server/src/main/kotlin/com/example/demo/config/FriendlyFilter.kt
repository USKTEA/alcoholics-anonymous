package com.example.demo.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class FriendlyFilter(
    private val authenticationManager: AuthenticationManager
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (hasAuthenticated()) {
            filterChain.doFilter(request, response)

            return
        }

        val requestToken = asAuthentication(request)

        if (requestToken == null) {
            filterChain.doFilter(request, response)

            return
        }

        val authentication = authenticationManager.authenticate(requestToken)

        if (authentication?.isAuthenticated != true) {
            return filterChain.doFilter(request, response)
        }

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun asAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader("Authorization")?.substring("Bearer ".length)

        return token?.let { ApiToken(it) }
    }

    private fun hasAuthenticated(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication

        return authentication?.isAuthenticated == true
    }
}