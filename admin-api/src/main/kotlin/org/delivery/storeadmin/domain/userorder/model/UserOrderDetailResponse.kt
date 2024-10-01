package org.delivery.storeadmin.domain.userorder.model

import org.delivery.storeadmin.domain.userordermenu.model.UserOrderMenuResponse

data class UserOrderDetailResponse(

    val userOrderResponse: UserOrderResponse,
    val userOrderMenuResponseList: List<UserOrderMenuResponse>,
)
