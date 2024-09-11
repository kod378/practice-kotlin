package org.delivery.storeadmin.domain.userorder.model

import org.delivery.storeadmin.domain.storemenu.model.StoreMenuResponse

data class UserOrderDetailResponse(

    val userOrderResponse: UserOrderResponse,
    val storeMenuResponseList: List<StoreMenuResponse>,
)
