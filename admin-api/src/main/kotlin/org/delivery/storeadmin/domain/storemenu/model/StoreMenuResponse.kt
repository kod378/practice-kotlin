package org.delivery.storeadmin.domain.storemenu.model

import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
