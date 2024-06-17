package com.oauth.resource_server.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController {

    @GetMapping
    fun getArticles(): List<String> {
        return listOf("Article1", "Article2", "Article3")
    }
}
