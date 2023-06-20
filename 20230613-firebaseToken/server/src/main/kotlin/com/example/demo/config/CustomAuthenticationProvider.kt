package com.example.demo.config

import com.example.demo.clients.FirebaseClient
import com.example.demo.models.Role
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication

class CustomAuthenticationProvider(
    private val firebaseClient: FirebaseClient
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val firebaseAuth = firebaseClient.getFirebaseAuth()

        val userDetail = authentication as UserDetail
        val jwtToken = userDetail.token()

        val decodedToken = firebaseAuth.verifyIdToken(jwtToken)
        val firebaseClaims = decodedToken.claims["firebase"] as Map<String, String>
        val signInProvider = firebaseClaims["sign_in_provider"]

        if (signInProvider == "anonymous") {
            authentication.addAuthority(Role.ROLE_ANONYMOUS)
            authentication.isAuthenticated = true
        }

        println(authentication.authorities)

        return authentication
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UserDetail::class.java == authentication
    }
}