package com.usktea.demo.controller

import com.usktea.demo.dtos.PaymentsDto
import com.usktea.demo.infra.ExcelFileResponseGenerator
import com.usktea.demo.services.PaymentService
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.core.io.FileSystemResource
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.logging.Logger

@RestController
@RequestMapping("payments")
class PaymentController(
    private val paymentService: PaymentService
) {

    @GetMapping("excel")
     fun getPaymentExcel(): ResponseEntity<FileSystemResource> {
        val sort = Sort.by(Sort.Direction.DESC, "id")
        val size = 5000
        var page = PageRequest.of(0, size, sort)
        val createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        val file = File.createTempFile("Payments_${createDate}", ".xlsx")
        file.deleteOnExit()

        val workbook: Workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Payment_$createDate")
        val headerFont = workbook.createFont()
        headerFont.bold = true
        headerFont.fontHeightInPoints = 14

        val headerCellStyle = workbook.createCellStyle()
        headerCellStyle.setFont(headerFont)
        val headerRow = sheet.createRow(0)
        val columns = arrayOf(
            "Price",
            "Amount",
            "Product_Name"
        )

        for (i in columns.indices) {
            val cell = headerRow.createCell(i)
            cell.setCellValue(columns[i])
            cell.cellStyle = headerCellStyle
        }

        val totalCount = paymentService.countAllPayments()
        var rowNumber = 1

        for (i: Long in 0..totalCount / size) {
            println(page.pageNumber)
            val payments = paymentService.getAllPayments(page)
            for (payment in payments) {
                val row = sheet.createRow(rowNumber)
                row.createCell(0).setCellValue(payment.price.toDouble())
                row.createCell(1).setCellValue(payment.amount.toDouble())
                row.createCell(2).setCellValue(payment.productName)
                rowNumber++
            }

            val fileOut = FileOutputStream(file)
            workbook.write(fileOut)
            fileOut.close()
            page = page.next()
        }

        workbook.close()
        return ExcelFileResponseGenerator.generate(file)
    }

    @GetMapping
    fun addPayments(): PaymentsDto {
        val count = paymentService.addPayments()

        return PaymentsDto(total = count)
    }
}