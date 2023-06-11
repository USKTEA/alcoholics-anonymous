package com.oop.sample.sample.services.impl

import com.oop.sample.sample.models.Result
import com.oop.sample.sample.dtos.ResultDto
import com.oop.sample.sample.dtos.ResultRequestDto
import com.oop.sample.sample.repositories.ResultRepository
import com.oop.sample.sample.services.ResultService
import com.oop.sample.sample.utils.Calculable
import com.oop.sample.sample.utils.Calculator
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ResultServiceImpl(
    private val resultRepository: ResultRepository,
    private val calculator: Calculable
) : ResultService {
    @Transactional
    override fun getResult(numberId: Long): ResultDto {
        val result = resultRepository.findByIdAndActive(numberId, true)

        result ?: throw EntityNotFoundException()

        return result.toDto()
    }

    @Transactional
    override fun createResult(resultRequestDto: ResultRequestDto): ResultDto {
        val operator = resultRequestDto.operator
        val firstOperand = resultRequestDto.firstOperand
        val secondOperand = resultRequestDto.secondOperand

        val value = calculator.calculate(
            operator = operator,
            firstOperand = firstOperand,
            secondOperand = secondOperand
        )

        val result = Result(value)

        val saved = resultRepository.save(result)

        return saved.toDto()
    }
}