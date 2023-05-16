package com.usktea.service

import com.usktea.dtos.OrderDto
import com.usktea.dtos.OrderRequestDto
import com.usktea.repository.ModuleCoreCustomerRepository
import com.usktea.repository.ModuleCoreOrderRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepository: ModuleCoreOrderRepository,
    private val customerRepository: ModuleCoreCustomerRepository
) {

    fun getOrders() = orderRepository.findAll()

    fun saveOrder(orderRequestDto: OrderRequestDto) {
        val customer =
            customerRepository.findById(orderRequestDto.customerId).orElseThrow { EntityNotFoundException("없어") }

        val orderDto = OrderDto(
            orderStoreName = orderRequestDto.orderStoreName,
            orderStoreAddress = orderRequestDto.orderStoreAddress,
            orderItem = orderRequestDto.orderItem,
            orderPrice = orderRequestDto.orderPrice,
            customer = customer
        )

        orderRepository.save(orderDto.toEntity())
    }
}