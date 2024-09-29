package org.delivery.storeadmin.domain.store.model

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
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
    val status: StoreStatus,
)
