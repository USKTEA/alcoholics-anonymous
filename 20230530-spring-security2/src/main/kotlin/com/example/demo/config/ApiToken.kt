package com.example.demo.config

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

open class ApiToken(val token: String) : Authentication {
    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        return mutableSetOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        TODO("Not yet implemented")
    }

    override fun getCredentials(): Any {
        TODO("Not yet implemented")
    }

    override fun getDetails(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any {
        TODO("Not yet implemented")
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}