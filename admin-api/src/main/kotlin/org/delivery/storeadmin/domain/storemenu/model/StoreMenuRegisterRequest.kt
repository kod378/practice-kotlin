package org.delivery.storeadmin.domain.storemenu.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class StoreMenuRegisterRequest(

    @field:NotBlank
    val name: String,

    @field:NotNull
    val amount: BigDecimal,

    @field:NotBlank
    val thumbnailUrl: String,
)
