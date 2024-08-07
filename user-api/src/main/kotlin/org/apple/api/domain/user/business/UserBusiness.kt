package org.apple.api.domain.user.business

import org.apple.api.common.annotation.Business
import org.apple.api.common.error.ErrorCode
import org.apple.api.common.exception.ApiException
import org.apple.api.domain.user.converter.UserConverter
import org.apple.api.domain.user.dto.UserRegisterRequestDto
import org.apple.api.domain.user.dto.UserResponseDto
import org.apple.api.domain.user.service.UserService

@Business
class UserBusiness(
    private val userService: UserService,
    private val userConverter: UserConverter
) {
    fun register(requestDto: UserRegisterRequestDto) : UserResponseDto {
        val user = userService.register(userConverter.toEntity(requestDto))
        return userConverter.toResponseDto(user)
    }
}