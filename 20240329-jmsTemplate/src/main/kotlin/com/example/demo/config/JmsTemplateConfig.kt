package com.example.demo.config

import jakarta.jms.ConnectionFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.boot.autoconfigure.jms.JmsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.destination.DestinationResolver

@Configuration
class LibraryActiveMqCommonConfigJmsListenerConfig {

    @Bean
    fun customizeJmsTemplate(
        properties: JmsProperties,
        destinationResolverObjectProvider: ObjectProvider<DestinationResolver>,
        messageConverterObjectProvider: ObjectProvider<MessageConverter>,
        connectionFactory: ConnectionFactory,
    ): JmsTemplate {
        val template = JmsTemplate(connectionFactory)

        template.isPubSubDomain = true

        val destinationResolver = destinationResolverObjectProvider.getIfUnique()
        if (destinationResolver != null) {
            template.destinationResolver = destinationResolver
        }

        val messageConverter = messageConverterObjectProvider.getIfUnique()
        if (messageConverter != null) {
            template.messageConverter = messageConverter
        }

        with(properties.template) {
            if (defaultDestination != null) {
                template.defaultDestinationName = defaultDestination
            }

            if (deliveryDelay != null) {
                template.deliveryDelay = deliveryDelay.toMillis()
            }

            template.isExplicitQosEnabled = determineQosEnabled()

            if (deliveryMode != null) {
                template.deliveryMode = deliveryMode.value
            }

            if (priority != null) {
                template.priority = priority
            }

            if (timeToLive != null) {
                template.timeToLive = timeToLive.toMillis()
            }

            if (receiveTimeout != null) {
                template.receiveTimeout = receiveTimeout.toMillis()
            }
        }

        return template
    }
}
