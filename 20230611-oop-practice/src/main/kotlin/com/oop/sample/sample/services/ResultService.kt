package com.oop.sample.sample.services

import com.oop.sample.sample.dtos.ResultDto
import com.oop.sample.sample.dtos.ResultRequestDto

interface ResultService {
    fun getResult(numberId: Long): ResultDto
    fun createResult(resultRequestDto: ResultRequestDto): ResultDto
}
