package org.delivery.api.domain.userorder.model

import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.api.domain.storemenu.model.StoreMenuResponse

data class UserOrderDetailResponse(
    val userOrderResponse: UserOrderResponse,
    val storeResponse: StoreResponse,
    val storeMenuResponseList: List<StoreMenuResponse>
)
