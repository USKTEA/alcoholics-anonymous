package com.example.demo.services

import org.springframework.stereotype.Service

@Service
class AddMinusService {
    fun add(name: String): String {
        return "$name-"
    }
}
