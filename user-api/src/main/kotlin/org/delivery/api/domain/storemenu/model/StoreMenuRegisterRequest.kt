package org.delivery.api.domain.storemenu.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class StoreMenuRegisterRequest(

    @field:NotNull
    val storeId: Long,

    @field:NotBlank
    val name: String,

    @field:NotNull
    val amount: BigDecimal,

    @field:NotBlank
    val thumbnailUrl: String,
)
