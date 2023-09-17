package com.usktea.demo.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.usktea.demo.dtos.ClovaRequestDto
import com.usktea.demo.dtos.ClovaResponseDto
import org.springframework.web.bind.annotation.*

@RequestMapping("/clova/devices")
@RestController
class DeviceController {

    @PostMapping
    fun commands(
        @RequestHeader headers: Map<String, String>,
        @RequestBody clovaRequestDto: ClovaRequestDto
    ): ClovaResponseDto {
        println("########")
        println(headers)
        println(clovaRequestDto)
        println("######")

        val json = """
        {
            "header": {
                "messageId": "99f9d8ff-9366-4cab-a90c-b4c7eca0abbe",
                "name": "DiscoverAppliancesResponse",
                "namespace": "ClovaHome",
                "payloadVersion": "1.0"
            },
            "payload": {
                "discoveredAppliances": [
                    {
                        "applianceId": "device-001",
                        "manufacturerName": "device-manufacturer-name",
                        "modelName": "스마트 전등",
                        "version": "v1.0",
                        "friendlyName": "거실 전등",
                        "friendlyDescription": "스마트폰으로 제어할 수 있는 전등",
                        "isIr": false,
                        "actions": [
                            "DecrementBrightness",
                            "HealthCheck",
                            "IncrementBrightness",
                            "SetBrightness",
                            "TurnOn",
                            "TurnOff"
                        ],
                        "applianceTypes": ["LIGHT"],
                        "additionalApplianceDetails": {},
                        "location": ""
                    },
                    {
                        "applianceId": "device-002",
                        "manufacturerName": "device-manufacturer-name",
                        "modelName": "스마트 플러그",
                        "version": "v1.0",
                        "friendlyName": "부엌 플러그",
                        "friendlyDescription": "에너지를 절약하는 플러그",
                        "isIr": false,
                        "actions": [
                            "HealthCheck",
                            "TurnOn",
                            "TurnOff"
                        ],
                        "applianceTypes": ["SMARTPLUG"],
                        "additionalApplianceDetails": {},
                        "location": "LIVING_ROOM"
                    }
                ]
            }
        }
    """.trimIndent()

        val response = jacksonObjectMapper().readValue(json, ClovaResponseDto::class.java)

        return response
    }
}
