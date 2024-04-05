package com.example.demo.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType

@Configuration
class MessageConfig {

//    @Bean
//    fun converter(objectMapper: ObjectMapper): MessageConverter = MappingJackson2MessageConverter().apply {
//        setObjectMapper(objectMapper)
//        setTargetType(MessageType.TEXT)
//        setTypeIdPropertyName("_type")
//    }
}
