package com.inheritence.demo.service

import com.inheritence.demo.entity.ManagerPost
import com.inheritence.demo.entity.Post
import com.inheritence.demo.entity.WriterOnlyPost
import com.inheritence.demo.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    init {
        val normal = Post(
            title = "normal",
            content = "normal",
            authorName = "abc"
        )

        val writerOnly = WriterOnlyPost(
            title = "writer", content = "writer", authorName = "abc"
        )

        val manager = ManagerPost(
            title = "manager", content = "manager", authorName = "abc"
        )

        postRepository.saveAll(listOf(normal, writerOnly, manager))
    }

    @Transactional
    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }
}
