package com.example.demo.controller

import com.example.demo.service.SseService
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/sse")
class SseController(
    private val sseService: SseService,
) {
    @GetMapping
    fun createSseConnection(
        @RequestParam id: String
    ) : SseEmitter {
        return sseService.connect(id)
    }
}
