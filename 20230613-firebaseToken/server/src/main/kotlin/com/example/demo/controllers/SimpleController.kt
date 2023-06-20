package com.example.demo.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {
    @RequestMapping("/")
    fun homeBears(): BearDto {
        return BearDto("homeBear")
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/users")
    fun userBears(): BearDto {
        return BearDto("userBear")
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admins")
    fun adminBears(): BearDto {
        return BearDto("adminBear")
    }

    @PreAuthorize("hasRole('ANONYMOUS')")
    @RequestMapping("/anonymous")
    fun anonymousBears(): BearDto {
        return BearDto("anonymousBear")
    }
}

data class BearDto(val bear: String)
