package com.example.demo.config

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class ApiToken : Authentication {
    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
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