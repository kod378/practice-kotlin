package org.delivery.storeadmin.domain.userorder.controller

import org.delivery.common.api.Api
import org.delivery.storeadmin.domain.userorder.business.UserOrderBusiness
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-order")
class UserOrderApiController(
    private val userOrderBusiness: UserOrderBusiness
) {

    @PutMapping("/:orderId/accept")
    fun accept(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.accept(orderId)
        return Api.OK(orderId);
    }

    @PutMapping("/:orderId/cancel")
    fun cancel(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.cancel(orderId)
        return Api.OK(orderId);
    }
}