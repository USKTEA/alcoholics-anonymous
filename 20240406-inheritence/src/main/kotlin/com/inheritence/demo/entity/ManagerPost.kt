package com.inheritence.demo.entity

import jakarta.persistence.Entity

@Entity
class ManagerPost(
    title: String,
    content: String,
    authorName: String,
    val password: String,
) : Post(title = title, content = content, authorName = authorName) {
    override fun authorize(member: Member): Boolean {
        return member is Manager
    }
}
