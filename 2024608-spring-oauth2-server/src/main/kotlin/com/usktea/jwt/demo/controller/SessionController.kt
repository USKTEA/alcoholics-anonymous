package com.usktea.jwt.demo.controller

import com.usktea.jwt.demo.controller.request.PostSessionRequest
import com.usktea.jwt.demo.service.PostSessionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val postSessionService: PostSessionService
) {

    @PostMapping
    fun session(
        @RequestBody postSessionRequest: PostSessionRequest
    ): String {
        return postSessionService.createSession(postSessionRequest)
    }
}
