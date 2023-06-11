package com.oop.sample.sample.endpoints

import com.oop.sample.sample.dtos.ResultDto
import com.oop.sample.sample.dtos.ResultRequestDto
import com.oop.sample.sample.services.ResultService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(ResultRouter::class, ResultHandler::class)
class ResultHandlerTest {
    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var resultService: ResultService

    @Test
    fun getNumber() {
        val resultId = 1L

        given(resultService.getResult(resultId)).willReturn(ResultDto(resultId, 0))

        client.get()
            .uri("/results/$resultId")
            .exchange()
            .expectStatus().isOk
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(resultId)
    }

    @Test
    fun createResult() {
        val resultRequestDto = ResultRequestDto(
            operator = "+",
            firstOperand = 1,
            secondOperand = 2
        )

        given(resultService.createResult(resultRequestDto))
            .willReturn(ResultDto(id = 1L, value = 3))

        client.post()
            .uri("/results")
            .bodyValue(resultRequestDto)
            .exchange()
            .expectStatus().isCreated
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").exists()
            .jsonPath("$.value").isEqualTo(3)
    }

}
