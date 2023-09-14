package com.usktea.demo

data class FormData(
    val grant_type: String,
    val client_id: String,
    val code: String,
    val redirect_uri: String
)
