package org.apple.api.domain.user.converter

import org.apple.api.common.annotation.Converter
import org.apple.api.common.error.UserErrorCode
import org.apple.api.common.exception.ApiException
import org.apple.api.domain.user.dto.UserRegisterRequestDto
import org.apple.api.domain.user.dto.UserResponseDto
import org.apple.db.user.UserEntity
import org.apple.db.user.enums.UserStatus

@Converter
class UserConverter {

    fun toEntity(userRegisterRequestDto: UserRegisterRequestDto)
    : UserEntity {
        return UserEntity(
            name = userRegisterRequestDto.name,
            email = userRegisterRequestDto.email,
            password = userRegisterRequestDto.password,
            status = UserStatus.REGISTERED,
        )
    }

    fun toResponseDto(userEntity: UserEntity)
    : UserResponseDto {
        return UserResponseDto(
            id = userEntity.id?: throw ApiException(UserErrorCode.USER_NOT_FOUND),
            name = userEntity.name,
            email = userEntity.email,
            address = userEntity.address,
            status = userEntity.status,
        )
    }
}