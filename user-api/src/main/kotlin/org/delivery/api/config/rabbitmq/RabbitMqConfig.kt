package org.delivery.api.config.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// Producer

@Configuration
class RabbitMqConfig {

    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange("delivery.exchange")
    }

    @Bean
    fun queue(): Queue {
        return Queue("delivery.queue")
    }

    @Bean
    fun binding(
        directExchange: DirectExchange,
        queue: Queue
    ): Binding {
        return BindingBuilder.bind(queue)
            .to(directExchange)
            .with("delivery.key")
    }

    // RabbitAdmin 추가: 이 Bean이 있어야 Exchange와 Queue가 자동 생성됩니다.
    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory): AmqpAdmin {
        return RabbitAdmin(connectionFactory)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }
}