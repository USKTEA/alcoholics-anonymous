package com.usktea.service

import com.usktea.dtos.CustomerDto
import com.usktea.entity.Customer
import com.usktea.repository.ModuleCoreCustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomerService(
    private val customerRepository: ModuleCoreCustomerRepository
) {

    fun saveCustomers(customerDto: CustomerDto) {
        this.customerRepository.save(customerDto.toEntity())
    }

    fun getCustomers(): MutableList<Customer> {
        return customerRepository.findAll()
    }
}
