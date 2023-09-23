package com.example.demo.controller

import com.example.demo.dtos.Request
import com.example.demo.services.GetCommendService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/commands")
class CommandController(
    private val getCommendService: GetCommendService,
) {

    @GetMapping
    fun getCommands(
        @RequestParam("command") command: String
    ): String {
        val producer = getCommendService.get(command)
        val request = Request.from(command)

        return producer.invoke(request).execute()
    }
}
