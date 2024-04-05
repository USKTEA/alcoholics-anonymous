package com.example.demo.controller

import com.example.demo.CustomJmsTemplate
import org.springframework.boot.autoconfigure.jms.JmsProperties
import org.springframework.jms.core.JmsTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class JmsController(
//    private val jmsTemplate1: CustomJmsTemplate,
    private val jmsProperties: JmsProperties,
    private val jmsTemplate: JmsTemplate,
) {

    @GetMapping
    fun test() {
        val jmsTemplate1 = jmsTemplate.messageConverter

    }
}
