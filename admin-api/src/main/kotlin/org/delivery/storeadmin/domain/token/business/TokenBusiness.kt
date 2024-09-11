package org.delivery.storeadmin.domain.token.business

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.storeadmin.domain.token.converter.TokenConverter
import org.delivery.storeadmin.domain.token.model.TokenResponse
import org.delivery.storeadmin.domain.token.service.TokenService
import org.delivery.common.annotation.Business
import org.delivery.db.storeuser.StoreUserEntity
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.storeuser.ifs.HasStore
import org.springframework.security.core.userdetails.UserDetails

@Business
class TokenBusiness(
    private val tokenService: TokenService,
    private val tokenConverter: TokenConverter
) {

    fun issueToken(username: String, hasStore: HasStore): TokenResponse {
        val accessToken = tokenService.issueAccessToken(username)
        val refreshToken = tokenService.issueRefreshToken(username)
        return tokenConverter.toResponseDto(accessToken, refreshToken, hasStore)
    }

    fun validateAccessToken(accessToken: String, userDetails: UserDetails): Boolean {
        return tokenService.validateToken(accessToken, userDetails)
    }

    fun getUsernameFromToken(token: String): String {
        return tokenService.getUsernameFromToken(token)
    }

    fun issueRefreshToken(userSession: UserSession): TokenResponse {
        val reAccessToken = tokenService.issueAccessToken(userSession.email)
        val reRefreshToken = tokenService.issueRefreshToken(userSession.email)
        return tokenConverter.toResponseDto(reAccessToken, reRefreshToken, userSession)
    }
}