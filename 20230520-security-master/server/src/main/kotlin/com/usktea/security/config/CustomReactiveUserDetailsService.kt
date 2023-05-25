package com.usktea.security.config

import com.usktea.security.repositories.AdminMemberRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class CustomerReactiveUserDetailsService(
    private val adminMemberRepository: AdminMemberRepository
) : ReactiveUserDetailsService {
    @Transactional(readOnly = true)
    override fun findByUsername(username: String?): Mono<UserDetails> {
        System.out.println("aaaaaa")
        val admin = adminMemberRepository.findByUsername(username!!).orElseThrow { throw BadCredentialsException("Invalid Credentials")}

        val authorities =
            admin!!.roles.stream().map { memberRole -> SimpleGrantedAuthority(memberRole.adminRoleType.name) }.toList()

        return Mono.just(User(admin.id!!.toString(), admin.password!!, authorities))
    }
}