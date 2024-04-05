package com.inheritence.demo.repository

import com.inheritence.demo.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
