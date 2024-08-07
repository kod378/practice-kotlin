package org.apple.api.domain.user.controller

import jakarta.validation.Valid
import org.apple.api.common.api.Api
import org.apple.api.domain.user.business.UserBusiness
import org.apple.api.domain.user.dto.UserRegisterRequestDto
import org.apple.api.domain.user.dto.UserResponseDto
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
        @Valid
        @RequestBody request: Api<UserRegisterRequestDto>
    ) : Api<UserResponseDto> {
        val response = userBusiness.register(request.body)
        return Api.OK(response)
    }

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody request: Api<UserLoginRequestDto>
    ){

    }
}