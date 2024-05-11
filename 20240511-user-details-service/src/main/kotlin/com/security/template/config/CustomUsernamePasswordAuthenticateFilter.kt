package com.security.template.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.context.DelegatingSecurityContextRepository
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class CustomUsernamePasswordAuthenticateFilter(
    httpSecurity: HttpSecurity
) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher("/api/login", "GET")
) {
    init {
        setSecurityContextRepository(
            getSecurityContextRepository(httpSecurity)
        )
    }

    private fun getSecurityContextRepository(http: HttpSecurity): SecurityContextRepository {
        val repository = http.getSharedObject(SecurityContextRepository::class.java)

        if (repository == null) {
            return DelegatingSecurityContextRepository(
                RequestAttributeSecurityContextRepository(), HttpSessionSecurityContextRepository()
            )
        }

        return repository
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val username = request.getParameter("user")
        val password = request.getParameter("password")

        return this.authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password, listOf(SimpleGrantedAuthority("ROLE_USER")))
        )
    }
}
