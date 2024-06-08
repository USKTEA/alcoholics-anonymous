package com.usktea.jwt.demo.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JwtUtilTest {
    private val jwtUtil = JwtUtil(secret = "thisIsSuperLongSecretToSecureEnoughForAnyJWTHMAC-SHAAlgorithm", expiration = 3600L)

    @Test
    fun createAndDecodeToken() {
        val username = "tjrxo1234"

        val token = jwtUtil.createToken(username)

        assertThat(token.split(".")).hasSize(3)

        val decoded = jwtUtil.decode(token)

        assertThat(decoded).isEqualTo(username)
    }
}
