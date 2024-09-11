package org.delivery.api.domain.userorder.producer

import org.delivery.api.common.rabbitmq.Producer
import org.delivery.common.message.model.UserOrderMessage
import org.delivery.db.userorder.UserOrderEntity
import org.springframework.stereotype.Service

const val EXCHANGE = "delivery.exchange"
const val ROUTE_KEY = "delivery.key"

@Service
class UserOrderProducer(
    private val producer: Producer
) {

    fun sendOrder(userOrderEntity: UserOrderEntity) {
        sendOrder(userOrderEntity.id!!)
    }

    fun sendOrder(userOrderId: Long) {
        val message = UserOrderMessage(userOrderId)

        producer.producer(EXCHANGE, ROUTE_KEY, message)
    }
}