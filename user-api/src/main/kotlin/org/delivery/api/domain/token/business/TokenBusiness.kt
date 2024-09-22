package org.delivery.api.domain.token.business

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.token.converter.TokenConverter
import org.delivery.api.domain.token.model.TokenResponse
import org.delivery.api.domain.token.service.TokenService
import org.delivery.common.annotation.Business
import org.delivery.db.user.UserEntity

@Business
class TokenBusiness(
    private val tokenService: TokenService,
    private val tokenConverter: TokenConverter
) {

    /**
     * 1. UserEntity user id 추출
     * 2. access, refresh token 발급
     * 3. 발급된 token을 response dto로 변환
     */
    fun issueToken(userEntity: UserEntity?): TokenResponse {
        val userId = userEntity?.id ?: throw ApiException(ErrorCode.NULL_POINTER, "userEntity is null")
        val accessToken = tokenService.issueAccessToken(userId)
        val refreshToken = tokenService.issueRefreshToken(userId)
        return tokenConverter.toResponseDto(accessToken, refreshToken, userEntity)
    }

    fun validateAccessToken(accessToken: String): Long {
        val userId = tokenService.validateToken(accessToken)
        return userId
    }
}