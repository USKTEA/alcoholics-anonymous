package com.usktea.demo.services

import com.usktea.demo.models.Payment
import com.usktea.demo.repositories.PaymentRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    @Transactional
    fun addPayments(): Long {
        val currentTotal = paymentRepository.count()

        for (i: Int in 1..100000) {
            val current = currentTotal + i
            paymentRepository.save(
                Payment(
                    price = i * 1000.toLong(),
                    amount = current,
                    productName = "상품$current"
                )
            )
        }

        return paymentRepository.count();
    }

    @Transactional
    fun countAllPayments(): Long {
        return paymentRepository.count()
    }

    @Transactional
    fun getAllPayments(page: PageRequest): Page<Payment> {
        return paymentRepository.findAll(page)
    }

    fun getAllPayments(): List<Payment> {
        return paymentRepository.findAll()
    }
}