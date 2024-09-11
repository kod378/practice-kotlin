package org.delivery.api.domain.user.controller

import io.swagger.v3.oas.annotations.Parameter
import org.delivery.api.common.annotation.UserSession
import org.delivery.common.api.Api
import org.delivery.api.domain.user.business.UserBusiness
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.user.model.UserResponse

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/user")
class UserApiController(
    private val userBusiness: UserBusiness
) {

    @GetMapping("/me")
    fun me(
        @Parameter(hidden = true) @UserSession userDto: User
    ): Api<UserResponse> {
        val response = userBusiness.me(userDto)
        return Api.OK(response)
    }
}