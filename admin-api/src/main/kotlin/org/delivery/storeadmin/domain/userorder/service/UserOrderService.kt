package org.delivery.storeadmin.domain.userorder.service

import org.delivery.common.error.UserOrderErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.UserOrderRepository
import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserOrderService(
    private val userOrderRepository: UserOrderRepository,
) {

    fun getUserOrder(userOrderId: Long): UserOrderEntity? {
        return userOrderRepository.findUserOrderById(userOrderId)
    }

    fun getUserOrderWithMenuList(userOrderId: Long): UserOrderEntity? {
        return userOrderRepository.findUserOrderWithMenuListById(userOrderId)
    }

    fun getUserOrderOrThrow(userOrderId: Long): UserOrderEntity {
        return getUserOrder(userOrderId) ?: throw ApiException(UserOrderErrorCode.ORDER_NOT_FOUND, "주문을 찾을 수 없습니다. userOrderId=$userOrderId")
    }
}