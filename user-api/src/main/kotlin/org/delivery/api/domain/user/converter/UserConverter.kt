package org.delivery.api.domain.user.converter

import org.delivery.common.error.UserErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.user.model.UserRegisterRequest
import org.delivery.api.domain.user.model.UserResponse
import org.delivery.common.annotation.Converter

@Converter
class UserConverter {

    fun toEntity(userRegisterRequestDto: UserRegisterRequest, userStatus: org.delivery.db.user.enums.UserStatus)
    : org.delivery.db.user.UserEntity {
        return org.delivery.db.user.UserEntity(
            name = userRegisterRequestDto.name,
            email = userRegisterRequestDto.email,
            password = userRegisterRequestDto.password,
            status = userStatus,
        )
    }

    fun toResponseDto(userEntity: org.delivery.db.user.UserEntity)
    : UserResponse {
        return UserResponse(
            id = userEntity.id?: throw ApiException(UserErrorCode.USER_NOT_FOUND),
            name = userEntity.name,
            email = userEntity.email,
//            address = userEntity.address,
            status = userEntity.status,
        )
    }
}