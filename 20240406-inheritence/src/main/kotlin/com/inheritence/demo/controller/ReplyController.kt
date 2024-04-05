package com.inheritence.demo.controller

import com.inheritence.demo.service.ReplyService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/replies")
class ReplyController(
    private val replyService: ReplyService,
) {
    @PostMapping("{id}")
    fun reply(
        @PathVariable id: Long
    ): Boolean {
        return replyService.reply(id)
    }

}
