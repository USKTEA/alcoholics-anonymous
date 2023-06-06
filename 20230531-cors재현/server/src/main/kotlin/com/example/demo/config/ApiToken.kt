package com.example.demo.config

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class ApiToken(val token: String) : Authentication {
    override fun getName(): String {
        return ""
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        if (token == "AdminBearToken") {
            return mutableSetOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        }

        return mutableSetOf()
    }

    override fun getCredentials(): Any {
        return ""
    }

    override fun getDetails(): Any {
        return ""
    }

    override fun getPrincipal(): Any {
        return ""
    }

    override fun isAuthenticated(): Boolean {
        return token == "AdminBearToken"
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}