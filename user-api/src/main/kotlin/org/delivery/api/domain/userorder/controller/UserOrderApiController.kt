package org.delivery.api.domain.userorder.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.delivery.api.common.annotation.UserSession
import org.delivery.common.api.Api
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userorder.business.UserOrderBusiness
import org.delivery.api.domain.userorder.model.UserOrderDetailResponse
import org.delivery.api.domain.userorder.model.UserOrderRequest
import org.delivery.api.domain.userorder.model.UserOrderResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-order")
class UserOrderApiController(
    private val userOrderBusiness: UserOrderBusiness
) {
    // 사용자 주문
    @PostMapping
    fun userOrder(
        @Parameter(hidden = true) @UserSession user: User,
        @Valid @RequestBody userOrderRequest: UserOrderRequest
    ): Api<UserOrderResponse> {
        val response = userOrderBusiness.userOrderAndSendOrder(user, userOrderRequest)
        return Api.OK(response)
    }

    // 현재 진행중인 주문건
    @GetMapping("/current")
    fun current(
        @Parameter(hidden = true) @UserSession user: User
    ): Api<List<UserOrderDetailResponse>> {
        val response = userOrderBusiness.current(user)
        return Api.OK(response)
    }

    // 과거 주문 내역
    @GetMapping("/history")
    fun history(
        @Parameter(hidden = true) @UserSession user: User
    ): Api<List<UserOrderDetailResponse>> {
        val response = userOrderBusiness.history(user)
        return Api.OK(response)
    }

    // 주문 1건에 대한 내역
    @GetMapping("/{userOrderId}")
    fun read(
        @Parameter(hidden = true) @UserSession user: User,
        @PathVariable userOrderId: Long
    ): Api<UserOrderDetailResponse> {
        val response = userOrderBusiness.read(user, userOrderId)
        return Api.OK(response)
    }
}