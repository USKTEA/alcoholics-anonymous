package com.usktea.jwt.demo.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberOnlyController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getMemberOnlySpecials(authentication: Authentication): String {
        println(authentication)
        return "Hello, Specials"
    }
}
