package org.delivery.api.common.rabbitmq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class Producer(
    private val rabbitTemplate: RabbitTemplate
) {

    fun producer(exchange: String, routeKey: String, any: Any) {
        rabbitTemplate.convertAndSend(exchange, routeKey, any)
    }

}