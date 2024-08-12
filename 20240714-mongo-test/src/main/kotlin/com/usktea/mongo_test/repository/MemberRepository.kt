package com.usktea.mongo_test.repository

import com.usktea.mongo_test.persistence.Member
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberRepository : MongoRepository<Member, String> {
    fun findByBlueId(blueId: String): Member?
}
