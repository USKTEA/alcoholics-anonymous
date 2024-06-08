package com.usktea.jwt.demo.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.session.SessionAuthenticationException
import org.springframework.security.web.context.DelegatingSecurityContextRepository
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class JwtAuthenticateFilter(
    authenticationManager: AuthenticationManager
) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher("/api/**")
) {

    init {
        setAuthenticationManager(authenticationManager)
    }


    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw SessionAuthenticationException("No JWT token found in request headers")
        }

        val token = authorizationHeader.substringAfter("Bearer ")
        val authenticationToken = JwtAuthenticationToken(token)

        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authResult
        SecurityContextHolder.setContext(context)

        chain.doFilter(request, response)
    }
}
