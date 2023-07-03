package com.usktea.demo.infra

import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.function.ServerResponse
import java.io.File

class ExcelFileResponseGenerator {
    companion object {
        suspend fun generate(file: File): ServerResponse {
            return ServerResponse.ok()
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION, "Content-Type")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.name)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header(HttpHeaders.SET_COOKIE, "fileDownload=false; path=/")
                .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                .header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
                .body(FileSystemResource(file))
        }
    }
}