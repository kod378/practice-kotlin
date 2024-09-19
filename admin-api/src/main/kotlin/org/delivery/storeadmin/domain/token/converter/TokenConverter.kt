package org.delivery.storeadmin.domain.token.converter

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.storeadmin.domain.token.model.Token
import org.delivery.storeadmin.domain.token.model.TokenResponse
import org.delivery.common.annotation.Converter
import org.delivery.storeadmin.domain.storeuser.ifs.HasStore

@Converter
class TokenConverter {

    fun toResponseDto(
        accessToken: Token?,
        refreshToken: Token?,
        hasStore: HasStore
    ): TokenResponse {

        if (accessToken == null || refreshToken == null) {
            throw ApiException(ErrorCode.NULL_POINTER, "accessToken or refreshToken is null")
        }

        return TokenResponse(
            accessToken = accessToken.token,
            accessTokenExpiredAt = accessToken.expiredAt,
            refreshToken = refreshToken.token,
            refreshTokenExpiredAt = refreshToken.expiredAt,
            storeResponse = hasStore.storeResponse
        )
    }
}