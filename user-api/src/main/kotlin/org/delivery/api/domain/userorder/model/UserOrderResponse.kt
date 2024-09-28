package org.delivery.api.domain.userorder.model

import org.delivery.db.userorder.enums.UserOrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserOrderResponse(
    val id: Long,
    val status: UserOrderStatus,
    val amount: BigDecimal,
    val orderNumber: String,
    val orderedAt: LocalDateTime,
    val acceptAt: LocalDateTime?,
    val cookingStartedAt: LocalDateTime?,
    val deliveryStartedAt: LocalDateTime?,
    val receivedAt: LocalDateTime?,
)
