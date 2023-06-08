package project.structure.admin.sample

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
        return sampleService.getSample().let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }

    suspend fun create(request: ServerRequest): ServerResponse {
        return sampleService.createSample().let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }
}