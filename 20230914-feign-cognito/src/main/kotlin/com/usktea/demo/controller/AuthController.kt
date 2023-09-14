package com.usktea.demo.controller

import com.usktea.demo.TokenDto
import com.usktea.demo.feign.CognitoFeign
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val cognitoFeign: CognitoFeign
) {
    val cognitoDomain = "https://usktea1234.auth.ap-northeast-2.amazoncognito.com"
    val clientId = "5cd6hh2ttlh6vqtfm19oj4m95i"
    val clientSecret = "m6vjstgm4lqas56kqartq3u4id5ekmd2n99l89ku5f40p926jc4"
    val redirectUri = "http://localhost:8080/auth/token"

    @GetMapping("/login")
    fun getLoginUrl(): ResponseEntity<Unit> {
        val url = ""

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, url)
            .build()
    }

    @GetMapping("/token")
    fun token(
        @RequestParam code: String
    ): TokenDto {
        val encoded = Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())
        val token = cognitoFeign.getToken(
            token = "Basic $encoded",
            request = tokenRequest(code)
        )

        return token
    }

//    private fun tokenRequest(code: String): FormData {
//        return FormData(
//            grant_type = "authorization_code",
//            client_id = clientId,
//            code = code,
//            redirect_uri = redirectUri
//        )
//    }

    @GetMapping("/foo")
    fun foo(): String {
        return "foo"
    }

    private fun tokenRequest(code: String): Map<String, String> {
        val formData = mutableMapOf<String, String>()

        ////val formData = LinkedMultiValueMap<String, String>()

        formData["grant_type"] = "authorization_code"
        formData["client_id"] = clientId
        formData["code"] = code
        formData["redirect_uri"] = redirectUri

        return formData
    }
}
