package com.usktea.mongo_test.controller

import com.usktea.mongo_test.repository.MemberRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val memberRepository: MemberRepository
) {

    @GetMapping
    fun test() {
        val member = memberRepository.findByBlueId("beom4653")!!

        println(member.birthdate)
    }
}
