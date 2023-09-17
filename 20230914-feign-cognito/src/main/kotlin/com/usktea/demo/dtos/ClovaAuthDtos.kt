package com.usktea.demo.dtos

data class ClovaAuthorizationUrlRequest(
    val clientId: String,
    val redirectUri: String,
    val responseType: String,
    val scope: String,
    val state: String,
) {
    companion object {
        fun of(
            clientId: String,
            redirectUri: String,
            responseType: String,
            scope: String,
            state: String,
        ): ClovaAuthorizationUrlRequest {
            return ClovaAuthorizationUrlRequest(
                clientId = clientId,
                redirectUri = redirectUri,
                responseType = responseType,
                scope = scope,
                state = state,
            )
        }
    }
}

data class ClaimTokenRequest(
    val clientId: String,
    val code: String,
    val grantType: String,
    val redirectUri: String,
) {
    companion object {
        fun of(clientId: String, code: String, grantType: String, redirectUri: String): ClaimTokenRequest {
            return ClaimTokenRequest(
                clientId = clientId,
                code = code,
                grantType = grantType,
                redirectUri = redirectUri,
            )
        }
    }
}

data class TokenDto(
    val access_token: String,
    val refresh_token: String,
    val id_token: String,
    val token_type: String,
    val expires_in: Int,
) {
    fun convertIdTokenToAccessToken(): TokenDto {
        return TokenDto(
            access_token = id_token,
            refresh_token = refresh_token,
            id_token = id_token,
            token_type = token_type,
            expires_in = expires_in,
        )
    }
}
