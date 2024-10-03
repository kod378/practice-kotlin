package org.delivery.storeadmin.domain.userorder.controller

import org.delivery.common.api.Api
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.userorder.business.UserOrderBusiness
import org.delivery.storeadmin.domain.userorder.model.UserOrderDetailResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-order")
class UserOrderApiController(
    private val userOrderBusiness: UserOrderBusiness
) {

    @PutMapping("/{orderId}/accept")
    fun accept(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.accept(orderId)
        return Api.OK(orderId);
    }

    @PutMapping("/{orderId}/cancel")
    fun cancel(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.cancel(orderId)
        return Api.OK(orderId);
    }

    @PutMapping("/{orderId}/cooking")
    fun cooking(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.cooking(orderId)
        return Api.OK(orderId);
    }

    @PutMapping("/{orderId}/delivery")
    fun delivery(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.delivery(orderId)
        return Api.OK(orderId);
    }

    @PutMapping("/{orderId}/receive")
    fun receive(
        @PathVariable orderId: Long
    ): Api<Long> {
        userOrderBusiness.receive(orderId)
        return Api.OK(orderId);
    }

    @GetMapping("/history")
    fun history(
        @AuthenticationPrincipal user: UserSession
    ): Api<List<UserOrderDetailResponse>> {
        return Api.OK(userOrderBusiness.history(user))
    }

}