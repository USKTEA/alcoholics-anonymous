package com.inheritence.demo.entity

import jakarta.persistence.Entity

@Entity
class WriterOnlyPost(
    title: String,
    content: String,
    authorName: String,
) : Post(title = title, content = content, authorName = authorName) {
    override fun authorize(member: Member): Boolean {
        return member.memberName == authorName
    }
}
