package org.delivery.storeadmin.domain.userorder.consumer

import org.delivery.common.log.logger
import org.delivery.common.message.model.UserOrderMessage
import org.delivery.storeadmin.domain.userorder.business.UserOrderBusiness
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class UserOrderConsumer(
    private val userOrderBusiness: UserOrderBusiness
) {

    val log = logger()

    @RabbitListener(queues = ["delivery.queue"])
    fun userOrderConsumer(userOrderMessage: UserOrderMessage) {
        log.info("message queue >> $userOrderMessage")
        userOrderBusiness.pushUserOrder(userOrderMessage)
    }
}