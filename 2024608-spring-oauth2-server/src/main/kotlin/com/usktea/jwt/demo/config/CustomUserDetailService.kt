package com.usktea.jwt.demo.config

import com.usktea.jwt.demo.model.Member
import com.usktea.jwt.demo.repository.MemberRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val found = memberRepository.findByUsername(username) ?: throw EntityNotFoundException()

        return CustomUserDetails(
            MemberDto.from(found)
        )
    }
}

data class MemberDto(
    val id: Long,
    val name: String,
    val username: String,
    val password: String,
    val roles: List<Member.Role>
) {
    companion object {
        fun from(member: Member): MemberDto {
            return MemberDto(
                id = member.id,
                name = member.name,
                username = member.username,
                password = member.password,
                roles = member.roles
            )
        }
    }
}

data class CustomUserDetails(
    val memberDto: MemberDto
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return memberDto.roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getPassword(): String {
        return memberDto.password
    }

    override fun getUsername(): String {
        return memberDto.username
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
