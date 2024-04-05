package com.inheritence.demo.service

import com.inheritence.demo.entity.Manager
import com.inheritence.demo.entity.Member
import com.inheritence.demo.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReplyService(
    private val postRepository: PostRepository,
) {
    fun reply(id: Long): Boolean {
        val post = postRepository.findByIdOrNull(id) ?: throw RuntimeException()

        val member = when(id) {
            1L -> Member(memberName = "abc")
            2L -> Member(memberName = "dd")
            else -> Manager(memberName = "d")
        }

        return post.authorize(member)
    }
}
