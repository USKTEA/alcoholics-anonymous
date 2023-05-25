package com.usktea.security.repositories

import com.usktea.security.entities.AdminMember
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AdminMemberRepository : JpaRepository<AdminMember, Long> {
    fun findByUsername(username: String): Optional<AdminMember>
}