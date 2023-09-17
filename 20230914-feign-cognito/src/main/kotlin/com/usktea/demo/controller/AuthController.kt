package com.usktea.demo.controller

import com.usktea.demo.adaptor.ClovaHomeNetAdaptor
import com.usktea.demo.dtos.ClaimTokenRequest
import com.usktea.demo.dtos.ClovaAuthorizationUrlRequest
import com.usktea.demo.dtos.TokenDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/clova")
class ClovaAuthController(
    private val clovaHomeNetAdaptor: ClovaHomeNetAdaptor,
) {

    @RequestMapping("/login")
    @ResponseStatus(HttpStatus.FOUND)
    fun getLoginUrl(
        @RequestParam("clientid") clientId: String,
        @RequestParam("redirect_uri") redirectUri: String,
        @RequestParam("response_type") responseType: String,
        @RequestParam("scope") scope: String,
        @RequestParam("state") state: String,
    ): ResponseEntity<Unit> {
        val url = clovaHomeNetAdaptor.getAuthorizationUrl(
            ClovaAuthorizationUrlRequest.of(
                clientId = clientId,
                redirectUri = redirectUri,
                responseType = responseType,
                scope = scope,
                state = state,
            ),
        )

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, url)
            .build()
    }

    @RequestMapping("/tokens")
    @ResponseStatus(HttpStatus.OK)
    fun requestToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("code") authorizationCode: String,
        @RequestParam("grant_type") grantType: String,
        @RequestParam("redirect_uri") redirectUri: String,
    ): TokenDto {
        return clovaHomeNetAdaptor.getAccessToken(
            ClaimTokenRequest.of(
                clientId = clientId,
                code = authorizationCode,
                grantType = grantType,
                redirectUri = redirectUri,
            ),
        )
    }
}
