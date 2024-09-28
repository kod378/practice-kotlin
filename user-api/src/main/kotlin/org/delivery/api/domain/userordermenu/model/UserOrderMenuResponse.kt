package org.delivery.api.domain.userordermenu.model

import java.math.BigDecimal

data class UserOrderMenuResponse(
    val id: Long,
    val name: String,
    val amount: BigDecimal,
    val quantity: Int,
)
