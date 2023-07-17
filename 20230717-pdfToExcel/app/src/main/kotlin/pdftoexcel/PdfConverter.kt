package pdftoexcel

import com.spire.pdf.FileFormat
import com.spire.pdf.PdfDocument
import com.aspose.pdf.Document
import com.aspose.pdf.SaveFormat

class PdfConverter {
    fun convertWithSpire() {
        val pdf = PdfDocument()
        pdf.loadFromFile("Inspection Report_230321.pdf")

        pdf.saveToFile("Inspection Report_230321.xlsx", FileFormat.XLSX)
    }

    fun convertWithAspose() {
        val doc = Document("Inspection Report_230321.pdf")
        doc.save("aspose.xlsx", SaveFormat.Excel)
    }
}