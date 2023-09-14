package com.usktea.demo.feign

import com.usktea.demo.TokenDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "CognitoFeign", url = "\${cognito.domain-url}")
interface CognitoFeign {

    @PostMapping("/oauth2/token", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(
        @RequestHeader("Authorization") token: String,
        @RequestBody request: Map<String, Any>
    ): TokenDto
}
