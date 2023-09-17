package com.usktea.demo.dtos

data class ClovaRequestDto(
    val header: Header,
    val payload: RequestPayload,
)

data class ClovaResponseDto(
    val header: Header,
    val payload: ResponsePayload,
)

data class Header(
    val messageId: String,
    val name: String,
    val namespace: String,
    val payloadVersion: String,
)

data class RequestPayload(
    val accessToken: String,
)

data class Appliance(
    val applianceId: String,
    val manufacturerName: String,
    val modelName: String,
    val version: String,
    val friendlyName: String,
    val friendlyDescription: String,
    val isIr: Boolean,
    val actions: List<String>,
    val applianceTypes: List<String>,
    val additionalApplianceDetails: Map<String, Any>,
    val location: String,
)

data class ResponsePayload(
    val discoveredAppliances: List<Appliance>,
)
