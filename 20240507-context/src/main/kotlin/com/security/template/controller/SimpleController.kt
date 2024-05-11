package com.security.template.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/")
    fun welcome(): String {
        val context = SecurityContextHolder.getContextHolderStrategy().context
        val authentication = context.authentication

        println(authentication.name)
        println(authentication.principal)
        println(authentication.authorities)

        return "welcome"
    }

    @GetMapping("/anonymous")
    fun hello(): String {
        return "hello"
    }
}
