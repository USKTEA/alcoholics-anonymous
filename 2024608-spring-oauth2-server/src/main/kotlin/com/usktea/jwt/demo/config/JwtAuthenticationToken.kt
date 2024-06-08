package com.usktea.jwt.demo.config

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtAuthenticationToken(
    private val token: String = "",
) : AbstractAuthenticationToken(null) {

    private lateinit var userDetails: UserDetails

    constructor(token: String, userDetails: UserDetails) : this(token) {
        this.userDetails = userDetails
        isAuthenticated = true
    }

    override fun getCredentials(): String {
        return token
    }

    override fun getPrincipal(): Any {
        return userDetails
    }

    override fun getName(): String {
        return token
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return userDetails.authorities
    }
}
