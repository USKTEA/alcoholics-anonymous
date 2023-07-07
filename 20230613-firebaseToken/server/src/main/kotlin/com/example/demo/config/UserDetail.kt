package com.example.demo.config

import com.example.demo.models.Role
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class UserDetail(
    private val token: String,
) : Authentication {
    private var isAuthenticated: Boolean = false
    private val grantedAuthorities = mutableListOf<GrantedAuthority>()

    override fun getName(): String {
        return ""
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        return grantedAuthorities
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
        return isAuthenticated
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

    fun token(): String {
        return token
    }

    fun addAuthority(role: Role) {
        grantedAuthorities.add(SimpleGrantedAuthority(role.toString()))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDetail

        if (token != other.token) return false
        if (isAuthenticated != other.isAuthenticated) return false
        return grantedAuthorities == other.grantedAuthorities
    }

    override fun hashCode(): Int {
        var result = token.hashCode()
        result = 31 * result + isAuthenticated.hashCode()
        result = 31 * result + grantedAuthorities.hashCode()
        return result
    }


}