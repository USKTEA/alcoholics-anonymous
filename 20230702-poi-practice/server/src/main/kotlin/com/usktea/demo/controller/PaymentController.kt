package com.usktea.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("payments")
class PaymentController {

    @GetMapping("excel")
    fun getPaymentExcel(): String {
        println("dffdfd");
        return "22"
    }
}