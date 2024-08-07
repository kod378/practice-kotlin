package org.apple.api.domain.user.controller

import org.apple.api.common.api.Api
import org.apple.api.domain.user.business.UserBusiness
import org.apple.api.domain.user.dto.UserRegisterRequestDto
import org.apple.api.domain.user.dto.UserResponseDto

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/user")
class UserApiController(
    private val userBusiness: UserBusiness
) {

    @GetMapping("/me")
    fun me() : Api<UserResponseDto> {
        val responseME = UserResponseDto(
            name = "apple",
            email = "test@test.com",
            address = null,
            status = null,
        )
        return Api.OK(responseME)
    }
}