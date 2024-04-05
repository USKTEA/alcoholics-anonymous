package com.inheritence.demo.controller

import com.inheritence.demo.entity.Post
import com.inheritence.demo.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {
    @GetMapping
    fun getPosts(): List<Post> {
        return postService.getPosts()
    }
}
