package com.usktea.demo.controller

import com.usktea.demo.dtos.PaymentsDto
import com.usktea.demo.services.PaymentService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerResponse

@RestController
@RequestMapping("payments")
class PaymentController(
    private val paymentService: PaymentService
) {

    @GetMapping("excel")
    fun getPaymentExcel(): ServerResponse {
        val sort = Sort.by(Sort.Direction.DESC, "id")
        val page = 0
        val size = 5000

        val totalCount = paymentService.countAllPayments()

        for (i: Long in 0..totalCount / size) {
            val page = PageRequest.of(i.toInt(), size, sort)
            val payments = paymentService.getAllPayments(page)
            paymentService.paymentsToExcel(payments.toMutableList())
        }

        //TODO: Excel 분리 
        return ServerResponse.ok().body("as")
    }

    @GetMapping
    fun addPayments(): PaymentsDto {
        val count = paymentService.addPayments()

        return PaymentsDto(total = count)
    }
}