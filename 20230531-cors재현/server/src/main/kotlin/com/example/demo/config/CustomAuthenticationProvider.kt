package com.example.demo.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication

class CustomAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        return authentication
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return ApiToken::class.java == authentication
    }
}