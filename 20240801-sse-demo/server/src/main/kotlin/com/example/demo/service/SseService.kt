package com.example.demo.service

import com.example.demo.controller.TodoController
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class SseService {
    private val sseEmitters = ConcurrentHashMap<String, SseEmitter>()

    fun connect(id: String): SseEmitter {
        val sseEmitter = SseEmitter(60 * 10000L)

        sseEmitters[id] = sseEmitter

        sseEmitter.onTimeout { println("타임아웃") }
        sseEmitter.onCompletion { println("끗") }

        sseEmitter.send(
            SseEmitter.event()
                .id(UUID.randomUUID().toString())
                .name("test event")
                .data("this is test")
                .reconnectTime(5000L)
        )

        return sseEmitter
    }

    fun publish(author: String, task: TodoController.Task) {
       sseEmitters.entries.forEach { (id, emitter) ->
            try {
                emitter.send(
                    SseEmitter.event()
                        .id(task.id.toString())
                        .name("taskUpdated")
                        .data(task)
                        .reconnectTime(5000L)
                )
                println("Task published successfully")
            } catch (e: Exception) {
                println("Error publishing task: ${e.message}")
            }
        }
    }
}
