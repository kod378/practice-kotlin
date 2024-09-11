package org.delivery.storeadmin.domain.userorder.model

import org.delivery.db.userorder.enums.UserOrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserOrderResponse(

    val id: Long,
    val storeId: Long,
    val userId: Long,
    val status: UserOrderStatus,
    val amount: BigDecimal,
    val orderedAt: LocalDateTime,
    val acceptAt: LocalDateTime? = null,
    val cookingStartedAt: LocalDateTime? = null,
    val deliveryStartedAt: LocalDateTime? = null,
    val receivedAt: LocalDateTime? = null,
)
