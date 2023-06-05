package ideal.admin.test

import ideal.library.domain.test.service.TestService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class TestHandler(
    private val testService: TestService
) {

    suspend fun test(request: ServerRequest): ServerResponse {
        return testService.test("hello").let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }
}