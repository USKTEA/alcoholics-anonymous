package com.security.template.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.annotation.CurrentSecurityContext
import org.springframework.security.core.context.SecurityContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/")
    fun welcome(): String {
        return "welcomes"
    }

    @GetMapping("/anonymous")
    fun guest(): String {
        return "hello guest"
    }

    @GetMapping("/logoutSuccess")
    fun logout(): String {
        return "byebye"
    }

    @PreAuthorize("hasRole('ROLE_GUEST')")
    @GetMapping("/wow")
    fun wow(@CurrentSecurityContext context: SecurityContext): String {
        println(context.authentication.authorities)
        return "wow"
    }
}
