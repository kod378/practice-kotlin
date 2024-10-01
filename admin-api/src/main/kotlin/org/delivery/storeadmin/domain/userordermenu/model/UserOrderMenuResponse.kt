package org.delivery.storeadmin.domain.userordermenu.model

import java.math.BigDecimal

data class UserOrderMenuResponse(
    val menuId: Long,
    val name: String,
    val amount: BigDecimal,
    val quantity: Int,
)
