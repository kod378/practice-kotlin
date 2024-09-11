package org.delivery.storeadmin.domain.userordermenu.service

import org.delivery.db.userorder.enums.UserOrderMenuStatus
import org.delivery.db.userordermenu.UserOrderMenuEntity
import org.delivery.db.userordermenu.UserOrderMenuRepository
import org.springframework.stereotype.Service

@Service
class UserOrderMenuService(
    private val userOrderMenuRepository: UserOrderMenuRepository,
) {

    fun getUserOrderMenuList(userOrderId: Long?): List<UserOrderMenuEntity> {
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED)
            ?: throw RuntimeException("UserOrderMenu not found")
    }
}