package org.delivery.api.domain.user.controller

import jakarta.validation.Valid
import org.delivery.common.api.Api
import org.delivery.api.domain.token.model.TokenResponse
import org.delivery.api.domain.user.business.UserBusiness
import org.delivery.api.domain.user.model.UserLoginRequest
import org.delivery.api.domain.user.model.UserRegisterRequest
import org.delivery.api.domain.user.model.UserResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api/user")
class UserOpenApiController(
    private val userBusiness: UserBusiness
) {

    @PostMapping
    fun register(
        @Valid @RequestBody request: UserRegisterRequest
    ) : Api<TokenResponse> {
        val response = userBusiness.register(request)
        return Api.OK(response)
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: UserLoginRequest
    ): Api<TokenResponse> {
        val response = userBusiness.login(request)
        return Api.OK(response)
    }
}