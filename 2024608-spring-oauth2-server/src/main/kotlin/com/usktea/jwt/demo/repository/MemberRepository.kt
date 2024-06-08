package com.usktea.jwt.demo.repository

import com.usktea.jwt.demo.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsername(username: String): Member?
}
