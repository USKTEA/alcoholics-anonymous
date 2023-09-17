package com.usktea.demo.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "clova.cognito")
data class CognitoProperty(
    val domainUrl: String,
    val clientId: String,
    val secretKey: String,
)
