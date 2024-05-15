package com.security.template.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        if (username == "hey") {
            throw UsernameNotFoundException(username)
        }

        val userInformation = UserInformation(username)
        return CustomUserDetails.from(userInformation)
    }
}

data class UserInformation(
    val username: String,
    val password: String = "1111",
    val authority: MutableCollection<SimpleGrantedAuthority> = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
)

data class CustomUserDetails(
    private val userInformation: UserInformation
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
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

    companion object {
        fun from(userInformation: UserInformation): UserDetails {
            return CustomUserDetails(userInformation)
        }
    }
}

