package com.oop.sample.sample.services.impl

import com.oop.sample.sample.models.Result
import com.oop.sample.sample.dtos.ResultRequestDto
import com.oop.sample.sample.repositories.ResultRepository
import com.oop.sample.sample.utils.Calculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean

class ResultServiceImplTest {
    @MockBean
    private lateinit var resultRepository: ResultRepository
    private lateinit var resultServiceImpl: ResultServiceImpl

    @SpyBean
    val calculator: Calculator = Calculator()

    @BeforeEach
    fun setup() {
        resultRepository = mock(ResultRepository::class.java)
        resultServiceImpl = ResultServiceImpl(resultRepository, calculator)
    }

    @Test
    fun getResult() {
        val resultId = 1L

        given(resultRepository.findByIdAndActive(resultId, true))
            .willReturn(Result(resultId, 0))

        val numberDto = resultServiceImpl.getResult(resultId)

        assertThat(numberDto.id).isEqualTo(resultId)
    }

    @Test
    fun createResult() {
        val resultRequestDto = ResultRequestDto(
            operator = "+",
            firstOperand = 1,
            secondOperand = 2
        )

        given(resultRepository.save(any())).willReturn(
            Result(1L, 3)
        )

        val resultDto = resultServiceImpl.createResult(resultRequestDto)

        Mockito.verify(resultRepository).save(any())
        assertThat(resultDto.value).isEqualTo(3)
    }
}
