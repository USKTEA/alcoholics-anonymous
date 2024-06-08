package com.usktea.jwt.demo.util

import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.expiration}")
    private val expiration: Long
) {
    private var builder: JwtBuilder = Jwts.builder()
    private var parser: JwtParser = Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
        .build()

    fun createToken(username: String): String {
        val now = OffsetDateTime.now()
        val expired = now.plusSeconds(expiration)
        val key = Keys.hmacShaKeyFor(secret.toByteArray())

        return builder
            .subject(username)
            .issuedAt(Date.from(now.toInstant()))
            .expiration(Date.from(expired.toInstant()))
            .signWith(key)
            .compact()
    }

    fun decode(token: String): String {
        return parser.parseSignedClaims(token)
            .payload
            .subject
    }
}
