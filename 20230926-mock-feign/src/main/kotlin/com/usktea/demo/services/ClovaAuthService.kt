package com.usktea.demo.services

import com.usktea.demo.dtos.ClaimTokenRequest
import com.usktea.demo.dtos.ClovaAuthorizationUrlRequest
import com.usktea.demo.dtos.TokenDto
import com.usktea.demo.feign.ClovaCognitoPlayerFeignClient
import com.usktea.demo.properties.CognitoProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.util.*

@Service
@EnableConfigurationProperties(CognitoProperty::class)
class ClovaAuthService(
    private val cognitoProperty: CognitoProperty,
    private val clovaCognitoPlayerFeignClient: ClovaCognitoPlayerFeignClient,
) {

    fun getAuthorizationUrl(request: ClovaAuthorizationUrlRequest): String {
        return cognitoProperty.domainUrl + "/login?" +
                "client_id=" + request.clientId +
                "&state=" + request.state +
                "&response_type=" + request.responseType +
                "&scope=" + request.scope +
                "&redirect_uri=" + request.redirectUri
    }

    fun getAccessToken(claimTokenRequest: ClaimTokenRequest): TokenDto {
        return clovaCognitoPlayerFeignClient.getToken(
            request = tokenRequest(claimTokenRequest),
            token = basicToken(),
        )
    }

    private fun basicToken(): String {
        return BASIC + Base64.getEncoder()
            .encodeToString("${cognitoProperty.clientId}:${cognitoProperty.secretKey}".toByteArray())
    }

    private fun tokenRequest(claimTokenRequest: ClaimTokenRequest): MultiValueMap<String, String> {
        val formData = LinkedMultiValueMap<String, String>()
        formData[GRANT_TYPE] = claimTokenRequest.grantType
        formData[CLIENT_ID] = claimTokenRequest.clientId
        formData[CODE] = claimTokenRequest.code
        formData[REDIRECT_URI] = claimTokenRequest.redirectUri

        return formData
    }

    companion object {
        const val BASIC = "Basic "
        const val GRANT_TYPE = "grant_type"
        const val CLIENT_ID = "client_id"
        const val CODE = "code"
        const val REDIRECT_URI = "redirect_uri"
    }
}
