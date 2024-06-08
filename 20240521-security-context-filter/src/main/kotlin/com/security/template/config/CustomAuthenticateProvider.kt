package com.security.template.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.CachingUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticateProvider(
    private val customUserDetailsService: CustomUserDetailsService,
) :AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val user = customUserDetailsService.loadUserByUsername(authentication.name)

        if (user.username != "user") {
            throw RuntimeException("dd")
        }

        return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}
