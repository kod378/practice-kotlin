package org.delivery.api.domain.store.model

import org.delivery.db.store.enums.StoreCategory
import java.math.BigDecimal

data class StoreResponse(
    val id: Long,
    val name: String,
    val address: String,
    val category: StoreCategory,
    val star: Double,
    val thumbnailUrl: String,
    val minimumAmount: BigDecimal,
    val minimumDeliveryAmount: BigDecimal,
    val phoneNumber: String,
)
