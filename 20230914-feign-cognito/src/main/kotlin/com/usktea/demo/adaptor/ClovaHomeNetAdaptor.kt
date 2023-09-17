package com.usktea.demo.adaptor

import com.usktea.demo.dtos.ClaimTokenRequest
import com.usktea.demo.dtos.ClovaAuthorizationUrlRequest
import com.usktea.demo.dtos.TokenDto
import com.usktea.demo.services.ClovaAuthService
import org.springframework.stereotype.Component

@Component
class ClovaHomeNetAdaptor(
    private val clovaAuthService: ClovaAuthService,
) {
    fun getAuthorizationUrl(request: ClovaAuthorizationUrlRequest): String {
        return clovaAuthService.getAuthorizationUrl(request)
    }

    fun getAccessToken(claimTokenRequest: ClaimTokenRequest): TokenDto {
        return clovaAuthService.getAccessToken(claimTokenRequest)
    }
}
