package org.delivery.api.domain.userorder.converter

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.UserErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userorder.model.UserOrderResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.userorder.UserOrderEntity
import java.math.BigDecimal

@Converter
class UserOrderConverter {

    fun toEntity(
        user: User,
        store: StoreEntity,
        totalAmount: BigDecimal,
    ): UserOrderEntity {
        return UserOrderEntity(
            userId = user.id,
            store = store,
            amount = totalAmount,
        )
    }

    fun toResponse(userOrderEntity: UserOrderEntity): UserOrderResponse {
        return UserOrderResponse(
            id = userOrderEntity.id?: throw ApiException(ErrorCode.NULL_POINTER),
            status = userOrderEntity.status?: throw ApiException(ErrorCode.NULL_POINTER),
            amount = userOrderEntity.amount,
            orderedAt = userOrderEntity.orderedAt,
            acceptAt = userOrderEntity.acceptAt,
            cookingStartedAt = userOrderEntity.cookingStartedAt,
            deliveryStartedAt = userOrderEntity.deliveryStartedAt,
            receivedAt = userOrderEntity.receivedAt,
            orderNumber = userOrderEntity.orderNumber
        )
    }
}