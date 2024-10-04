package org.delivery.api.config.rabbitmq

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqInitConfig(
    private val rabbitAdmin: AmqpAdmin
) {

    init {
        rabbitAdmin.initialize()
    }
}