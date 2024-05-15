package com.security.template.provider

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.CachingUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticateProvider(
    private val userDetailsService: CachingUserDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val userInfo = userDetailsService.loadUserByUsername(authentication.name)


    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}
