package org.delivery.storeadmin.domain.store.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.delivery.db.store.enums.StoreCategory
import java.math.BigDecimal

data class StoreRegisterRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val address: String,
    @field:NotNull
    val category: StoreCategory,
    @field:NotBlank
    val thumbnailUrl: String,
    @field:NotNull
    val minimumAmount: BigDecimal,
    @field:NotNull
    val minimumDeliveryAmount: BigDecimal,
    @field:NotBlank
    val phoneNumber: String,
)
