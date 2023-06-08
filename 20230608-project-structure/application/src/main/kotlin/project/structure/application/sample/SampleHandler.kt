package project.structure.application.sample

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import project.structure.sample.service.SampleService

@Component
class SampleHandler(
    private val sampleService: SampleService
) {
    suspend fun test(request: ServerRequest): ServerResponse {
        return sampleService.test().let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }
}