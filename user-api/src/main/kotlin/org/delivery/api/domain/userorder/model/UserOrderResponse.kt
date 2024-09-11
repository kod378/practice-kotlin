package org.delivery.api.domain.userorder.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class UserOrderResponse(
    val id: Long,
    val status: org.delivery.db.userorder.enums.UserOrderStatus,
    val amount: BigDecimal,
    val orderedAt: LocalDateTime,
    val acceptAt: LocalDateTime?,
    val cookingStartedAt: LocalDateTime?,
    val deliveryStartedAt: LocalDateTime?,
    val receivedAt: LocalDateTime?,
)
