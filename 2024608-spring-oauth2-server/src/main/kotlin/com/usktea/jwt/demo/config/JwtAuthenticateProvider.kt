package com.usktea.jwt.demo.config

import com.usktea.jwt.demo.util.JwtUtil
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthenticateProvider(
    private val customUserDetailService: CustomUserDetailService,
    private val jwtUtil: JwtUtil,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.name
        val username = jwtUtil.decode(token)

        val userDetails = customUserDetailService.loadUserByUsername(username)

        return JwtAuthenticationToken(token, userDetails)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(JwtAuthenticationToken::class.java)
    }
}
