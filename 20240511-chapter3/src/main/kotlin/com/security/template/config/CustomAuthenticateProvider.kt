package com.security.template.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomAuthenticateProvider(
    private val userDetailsService: UserDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        if (authentication.name != "user") {
            throw UsernameNotFoundException("Not Found")
        }

        val userDetails = userDetailsService.loadUserByUsername(authentication.name)

        return UsernamePasswordAuthenticationToken(
            userDetails.username,
            userDetails.password,
            userDetails.authorities
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}
