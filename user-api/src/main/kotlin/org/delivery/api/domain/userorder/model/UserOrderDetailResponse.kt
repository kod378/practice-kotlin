package org.delivery.api.domain.userorder.model

import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.api.domain.userordermenu.model.UserOrderMenuResponse

data class UserOrderDetailResponse(
    val userOrderResponse: UserOrderResponse,
    val storeResponse: StoreResponse,
    val userOrderMenuResponseList: List<UserOrderMenuResponse>
)
