package com.oop.sample.sample.endpoints

import com.oop.sample.sample.dtos.ResultDto
import com.oop.sample.sample.dtos.ResultRequestDto
import com.oop.sample.sample.services.ResultService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI

@Component
class ResultHandler(
    private val resultService: ResultService
) {
    private val path = "/results"

    suspend fun getResult(serverRequest: ServerRequest) : ServerResponse {
        val id = serverRequest.pathVariable("resultId").toLong()
        val result = resultService.getResult(id)

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(result)
    }

    suspend fun createResult(serverRequest: ServerRequest) : ServerResponse {
        val resultRequestDto = serverRequest.awaitBody<ResultRequestDto>()
        val result = resultService.createResult(resultRequestDto)
        val uri = URI("$path/${result.id}")

        return ServerResponse.created(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(result)
    }
}
