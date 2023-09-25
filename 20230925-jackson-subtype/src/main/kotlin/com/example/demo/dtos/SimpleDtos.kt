package com.example.demo.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes

import com.fasterxml.jackson.annotation.JsonTypeInfo

//
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "header.name", visible = true)
//@JsonSubTypes(
//    JsonSubTypes.Type(value = ClovaDiscoverAppliancesRequest::class, name = "DiscoverAppliancesRequest"),
//    JsonSubTypes.Type(value = ClovaGetAirQualityRequest::class, name = "GetAirQualityRequest"),
//)
//abstract class ClovaRequest

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = ClovaDiscoverAppliancesRequest::class, name = "DiscoverAppliancesRequest"),
    JsonSubTypes.Type(value = ClovaGetAirQualityRequest::class, name = "GetAirQualityRequest"),
)
abstract class ClovaRequest

data class ClovaDiscoverAppliancesRequest (
    val header: Header,
//    val name: String,
    val payload: DiscoverAppliancesRequestPayload,
) : ClovaRequest()

data class ClovaGetAirQualityRequest(
    val name: String,
    val payload: GetAirQualityRequestPayload,
) : ClovaRequest()

data class Header(
    val messageId: String,
    val name: String,
    val namespace: String,
    val payloadVersion: String,
)

data class DiscoverAppliancesRequestPayload(
    val accessToken: String
)

data class GetAirQualityRequestPayload(
    val accessToken: String,
    val appliance: Appliance,
)

data class Appliance(
    val applianceId: String
)
