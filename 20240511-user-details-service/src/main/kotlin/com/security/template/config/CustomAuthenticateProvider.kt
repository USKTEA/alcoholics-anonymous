package com.security.template.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomAuthenticateProvider(
    private val userDetailsService: UserDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val user = userDetailsService.loadUserByUsername(authentication.name)

        return UsernamePasswordAuthenticationToken(
            user.username, user.password, user.authorities
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}
