package org.delivery.api.domain.token.converter

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.token.model.Token
import org.delivery.api.domain.token.model.TokenResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.user.UserEntity

@Converter
class TokenConverter {

    fun toResponseDto(
        accessToken: Token?,
        refreshToken: Token?,
        userEntity: UserEntity
    ): TokenResponse {

        if (accessToken == null || refreshToken == null) {
            throw ApiException(ErrorCode.NULL_POINTER, "accessToken or refreshToken is null")
        }

        return TokenResponse(
            accessToken = accessToken.token,
            accessTokenExpiredAt = accessToken.expiredAt,
            refreshToken = refreshToken.token,
            refreshTokenExpiredAt = refreshToken.expiredAt,

            userId = userEntity.id!!,
            username = userEntity.name
        )
    }
}