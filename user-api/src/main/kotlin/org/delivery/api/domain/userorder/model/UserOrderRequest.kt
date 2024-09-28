package org.delivery.api.domain.userorder.model

import jakarta.validation.constraints.NotNull

data class UserOrderRequest(

    @field:NotNull
    val storeId: Long,

    @field:NotNull
    val userOrderMenuRequestList: List<UserOrderMenuRequest>
)
