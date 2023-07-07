package project.structure.application.enpoint.sample

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import project.structure.application.infra.InfraSomething
import project.structure.sample.service.SampleService

@Component
class SampleHandler(
    private val sampleService: SampleService,
    private val infraSomething: InfraSomething
) {
    suspend fun test(request: ServerRequest): ServerResponse {
        return sampleService.getSample().let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }

    suspend fun create(request: ServerRequest): ServerResponse {

        val key = infraSomething.key()

        return sampleService.createSample(key).let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }
}