package com.example.demo

import jakarta.jms.ConnectionFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.stereotype.Component

//@Component
class CustomJmsTemplate(
    connectionFactory: ConnectionFactory
) : JmsTemplate(connectionFactory) {
    init {
        messageConverter = MappingJackson2MessageConverter()
    }
}
