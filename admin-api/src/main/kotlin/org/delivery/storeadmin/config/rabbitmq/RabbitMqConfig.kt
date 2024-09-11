package org.delivery.storeadmin.config.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// Consumer

@Configuration
class RabbitMqConfig {

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }
}