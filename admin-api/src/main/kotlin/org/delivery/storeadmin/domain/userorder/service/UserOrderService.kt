package org.delivery.storeadmin.domain.userorder.service

import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.UserOrderRepository
import org.springframework.stereotype.Service

@Service
class UserOrderService(
    private val userOrderRepository: UserOrderRepository,
) {

    fun getUserOrder(userOrderId: Long): UserOrderEntity? {
        return userOrderRepository.findUserOrderById(userOrderId)
    }
}