package com.security.template.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.context.DelegatingSecurityContextRepository
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class CustomAuthenticateFilter(
    httpSecurity: HttpSecurity
) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher("/api/login", "GET")
) {
    init {
        getSecurityContextRepository(httpSecurity)
    }

    fun getSecurityContextRepository(httpSecurity: HttpSecurity): SecurityContextRepository {
        var repository = httpSecurity.getSharedObject(SecurityContextRepository::class.java)

        if (repository == null) {
            repository = DelegatingSecurityContextRepository(
                RequestAttributeSecurityContextRepository(), HttpSessionSecurityContextRepository()
            )
        }

        return repository
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val username = request.getParameter("username")
        val password = request.getParameter("password")

        val authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
            username,
            password
        );

        return this.authenticationManager.authenticate(authRequest)
    }
}
