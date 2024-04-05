package com.inheritence.demo.entity

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val title: String,
    val content: String,
    val authorName: String,
) {
    fun authorize(member: Member): Boolean {
        return true
    }
}
