package org.delivery.api.domain.store.model

import org.delivery.api.domain.storemenu.model.StoreMenuResponse
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import java.math.BigDecimal

data class StoreInfoResponse(
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
    val menus: List<StoreMenuResponse>
)