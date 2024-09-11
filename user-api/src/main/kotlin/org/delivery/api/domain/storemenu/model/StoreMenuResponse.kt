package org.delivery.api.domain.storemenu.model

import java.math.BigDecimal

data class StoreMenuResponse(
    val id: Long,
    val storeId: Long,
    val name: String,
    val amount: BigDecimal,
    val status: org.delivery.db.storemenu.enums.StoreMenuStatus,
    val thumbnailUrl: String,
    val likeCount: Int,
    val sequence: Int,
)
