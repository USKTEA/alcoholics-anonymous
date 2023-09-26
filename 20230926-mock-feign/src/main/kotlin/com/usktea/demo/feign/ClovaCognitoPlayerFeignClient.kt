package com.usktea.demo.feign

import com.usktea.demo.dtos.TokenDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@Profile("prod")
@FeignClient(name = "ClovaCognitoPlayerFeignClient", url = "\${clova.cognito.domain-url}")
interface ClovaCognitoPlayerFeignClient {

    @PostMapping("/oauth2/token", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
        @RequestBody request: Map<String, Any>
    ): TokenDto
}

@Component
class ClovaCognitoPlayerFeignClientDev : ClovaCognitoPlayerFeignClient {
    override fun getToken(token: String, request: Map<String, Any>): TokenDto {
        return TokenDto(
            access_token = "sss",
            refresh_token = "ddd",
            id_token = "aaa",
            token_type = "ooo",
            expires_in = 11,
        )
    }
}
