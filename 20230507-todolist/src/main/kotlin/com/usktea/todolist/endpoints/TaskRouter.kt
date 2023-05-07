package com.usktea.todolist.endpoints

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TaskRouter {
    val basePath = "/tasks"

    @Bean
    fun taskRoutes(handler: TaskHandler) = coRouter {
        path(basePath).nest {
            GET("", handler::list)
            GET("{id}", handler::task)
        }
    }
}