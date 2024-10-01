package org.delivery.storeadmin.domain.userordermenu.converter

import org.delivery.common.annotation.Converter
import org.delivery.db.userordermenu.UserOrderMenuEntity
import org.delivery.storeadmin.domain.userordermenu.model.UserOrderMenuResponse

@Converter
class UserOrderMenuConverter {

    fun toResponse(
        userOrderMenuEntity: UserOrderMenuEntity,
    ): UserOrderMenuResponse {
        return UserOrderMenuResponse(
            menuId = userOrderMenuEntity.storeMenu.id!!,
            name = userOrderMenuEntity.storeMenu.name,
            amount = userOrderMenuEntity.storeMenu.amount,
            quantity = userOrderMenuEntity.quantity,
        )
    }

    fun toResponse(
        userOrderMenuEntityList: List<UserOrderMenuEntity>,
    ): List<UserOrderMenuResponse> {
        return userOrderMenuEntityList.map {
            toResponse(it)
        }
    }
}