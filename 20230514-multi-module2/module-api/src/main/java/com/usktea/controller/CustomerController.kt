package com.usktea.controller

import com.usktea.dtos.CustomerDto
import com.usktea.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping("/customers")
    fun saveCustomers(@RequestBody customerDto: CustomerDto) {
        return this.customerService.saveCustomers(customerDto)
    }

    @GetMapping("/customers")
    fun getCustomers() = this.customerService.getCustomers()
}
