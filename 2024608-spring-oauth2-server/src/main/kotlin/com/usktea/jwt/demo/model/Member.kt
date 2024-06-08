package com.usktea.jwt.demo.model

import jakarta.persistence.*
import java.lang.IllegalArgumentException

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String,
    val username: String,
    val password: String,
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val roles: MutableList<Role>,
) {
    fun checkPassword(password: String) {
        if (this.password != password) {
            throw IllegalArgumentException()
        }
    }

    enum class Role {
        ROLE_USER, ROLE_ADMIN
    }
}
