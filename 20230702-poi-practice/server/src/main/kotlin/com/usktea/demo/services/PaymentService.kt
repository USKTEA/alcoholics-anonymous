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

        for (i: Int in 1..10000) {
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

//    fun paymentsToExcel(payments: MutableList<Payment>): File? {
//
//        try {
//            val createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
//            val workbook: Workbook = XSSFWorkbook()
//
//            val sheet = workbook.createSheet("Payment_$createDate")
//
//            val headerFont = workbook.createFont()
//            headerFont.bold = true
//            headerFont.fontHeightInPoints = 14
//
//            val headerCellStyle = workbook.createCellStyle()
//            headerCellStyle.setFont(headerFont)
//            val headerRow = sheet.createRow(0)
//            val columns = arrayOf(
//                "Price",
//                "amount",
//                "ProductName"
//            )
//            for (i in columns.indices) {
//                val cell = headerRow.createCell(i)
//                cell.setCellValue(columns[i])
//                cell.cellStyle = headerCellStyle
//            }
//
//            var rowNum = 1
//            for (payment in payments) {
//                val row = sheet.createRow(rowNum)
//                row.createCell(0).setCellValue(payment.price.toDouble())
//                row.createCell(1).setCellValue(payment.amount.toDouble())
//                row.createCell(2).setCellValue(payment.productName)
//                rowNum++
//            }
//
//            for (i in columns.indices) {
//                sheet.autoSizeColumn(i)
//            }
//
//            val file = File.createTempFile("payments_${createDate}", ".xlsx")
//            file.deleteOnExit()
//            val fileOut = FileOutputStream(file)
//            workbook.write(fileOut)
//            fileOut.close()
//            workbook.close()
//            return file
//        } catch (e: IOException) {
//            throw IOException(e)
//        }
//    }
}