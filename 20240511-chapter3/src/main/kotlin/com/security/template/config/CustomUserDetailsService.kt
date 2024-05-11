package com.security.template.config

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val userInformation = UserInformation(username)

        return CustomUserDetails(userInformation)
    }
}

data class UserInformation(
    val username: String,
    val password: String = "1111",
    val authority: List<SimpleGrantedAuthority> = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
)

data class CustomUserDetails(
    private val userInformation: UserInformation
) : UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return userInformation.authority
    }

    override fun getPassword(): String {
        return userInformation.password
    }

    override fun getUsername(): String {
        return userInformation.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
