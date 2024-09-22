package org.delivery.api.domain.storemenu.model

import org.delivery.db.storemenu.enums.StoreMenuStatus
import java.math.BigDecimal

data class StoreMenuResponse(
    val id: Long,
    val name: String,
    val amount: BigDecimal,
    val status: StoreMenuStatus,
    val thumbnailUrl: String,
    val likeCount: Int,
    val sequence: Int
)
