package com.example.demo.services

import org.springframework.stereotype.Service

@Service
class AddPlusService {
    fun add(name: String): String {
        return "$name+"
    }
}
