package com.oauth.client.controller

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val webClient: WebClient,
) {

    @GetMapping
    fun getArticles(
        @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code")
        authorizedClient: OAuth2AuthorizedClient
    ): List<String> {

        return this.webClient
            .get()
            .uri("http://127.0.0.1:8090/articles")
            .attributes(oauth2AuthorizedClient(authorizedClient))
            .retrieve()
            .bodyToMono<List<String>>()
            .block()!!
    }
}
