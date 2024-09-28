package org.delivery.api.domain.userorder.model

data class UserOrderMenuRequest(
    val storeMenuId: Long,
    val quantity: Int,
)
