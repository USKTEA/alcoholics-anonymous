package com.security.template.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
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
    httpSecurity: HttpSecurity,
    authenticationManager: AuthenticationManager,
) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher("/api/login")
) {

    init {
        this.authenticationManager = authenticationManager

        setAuthenticationSuccessHandler { request, response, authentiacation ->
            response.sendRedirect("/success")
        }

        setAuthenticationFailureHandler { request, response, authentiacation ->
            response.sendRedirect("/failed")
        }

        setSecurityContextRepository(getSecurityContextRepository(httpSecurity))
    }

    private fun getSecurityContextRepository(httpSecurity: HttpSecurity): SecurityContextRepository {
        val repository = httpSecurity.getSharedObject(SecurityContextRepository::class.java)

        if (repository == null) {
            return DelegatingSecurityContextRepository(
                HttpSessionSecurityContextRepository(), RequestAttributeSecurityContextRepository()
            )
        }

        return repository
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val username = request.getParameter("username")
        val password = request.getParameter("password")


        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: Exception) {
            // Redirect to the failure URL
            response.sendRedirect("/failed")
            throw e
        }
    }
}
