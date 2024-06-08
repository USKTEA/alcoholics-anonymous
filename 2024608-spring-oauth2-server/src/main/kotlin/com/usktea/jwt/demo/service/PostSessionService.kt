package com.usktea.jwt.demo.service

import com.usktea.jwt.demo.controller.request.PostSessionRequest
import com.usktea.jwt.demo.model.Member
import com.usktea.jwt.demo.repository.MemberRepository
import com.usktea.jwt.demo.util.JwtUtil
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class PostSessionService(
    private val memberRepository: MemberRepository,
    private val jwtUtil: JwtUtil,
) {
    init {
        memberRepository.deleteAll()
        memberRepository.save(
            Member(
                name = "suktae",
                username = "sukate",
                password = "1q2w3e4r",
                roles = mutableListOf(Member.Role.ROLE_USER)
            )
        )
        memberRepository.save(
            Member(
                name = "notsuktae",
                username = "test",
                password = "1q2w3e4r",
                roles = mutableListOf(Member.Role.ROLE_ADMIN)
            )
        )
    }

    fun createSession(postSessionRequest: PostSessionRequest): String {
        val member = memberRepository.findByUsername(postSessionRequest.username) ?: throw EntityNotFoundException()

        member.checkPassword(postSessionRequest.password)

        return jwtUtil.createToken(member.username)
    }
}
