package org.delivery.storeadmin.domain.userorder.converter

import org.delivery.common.annotation.Converter
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.storeadmin.domain.userorder.model.UserOrderResponse

@Converter
class UserOrderConverter {

    fun toResponse(userOrderEntity: UserOrderEntity): UserOrderResponse {
        return UserOrderResponse(
            id = userOrderEntity.id!!,
            userId = userOrderEntity.userId,
            storeId = userOrderEntity.store.id!!,
            status = userOrderEntity.status,
            amount = userOrderEntity.amount,
            orderedAt = userOrderEntity.orderedAt,
            acceptAt = userOrderEntity.acceptAt,
            cookingStartedAt = userOrderEntity.cookingStartedAt,
            deliveryStartedAt = userOrderEntity.deliveryStartedAt,
            receivedAt = userOrderEntity.receivedAt,
        )
    }
}