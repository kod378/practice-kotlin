package org.delivery.api.domain.store.model

import java.math.BigDecimal

data class StoreResponse(
    val id: Long,
    val name: String,
    val address: String,
    val category: org.delivery.db.store.enums.StoreCategory,
    val star: Double,
    val thumbnailUrl: String,
    val minimumAmount: BigDecimal,
    val minimumDeliveryAmount: BigDecimal,
    val phoneNumber: String,
)
