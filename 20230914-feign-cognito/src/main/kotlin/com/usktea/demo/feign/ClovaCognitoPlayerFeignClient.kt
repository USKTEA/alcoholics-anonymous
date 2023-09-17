package com.usktea.demo.feign

import com.usktea.demo.dtos.TokenDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "ClovaCognitoPlayerFeignClient", url = "\${clova.cognito.domain-url}")
interface ClovaCognitoPlayerFeignClient {

    @PostMapping("/oauth2/token", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
        @RequestBody request: Map<String, Any>
    ): TokenDto
}
