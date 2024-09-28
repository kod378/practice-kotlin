package org.delivery.api.domain.userordermenu.converter

import org.delivery.api.domain.userordermenu.model.UserOrderMenuResponse
import org.delivery.common.annotation.Converter
import org.delivery.common.error.StoreMenuErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userordermenu.UserOrderMenuEntity

@Converter
class UserOrderMenuConverter {

    fun toEntity(
        userOrderEntity: UserOrderEntity,
        storeMenuEntity: StoreMenuEntity,
        quantity: Int,
    ): UserOrderMenuEntity {
        return UserOrderMenuEntity(
            userOrder = userOrderEntity,
            storeMenu = storeMenuEntity,
            quantity = quantity,
        )
    }

    fun toResponse(storeMenuList: List<UserOrderMenuEntity>): List<UserOrderMenuResponse> {
        return storeMenuList.map {
            UserOrderMenuResponse(
                id = it.storeMenu.id ?: throw ApiException(StoreMenuErrorCode.STORE_MENU_NOT_FOUND),
                name = it.storeMenu.name,
                amount = it.storeMenu.amount,
                quantity = it.quantity,
            )
        }
    }
}