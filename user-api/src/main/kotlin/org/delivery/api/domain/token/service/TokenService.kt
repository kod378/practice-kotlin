package org.delivery.api.domain.token.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.token.model.Token
import org.delivery.api.domain.token.ifs.TokenHelperIfs
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenHelperIfs: TokenHelperIfs
) {

    fun issueAccessToken(userId: Long): Token {
        val data = mapOf("userId" to userId)
        return tokenHelperIfs.issueAccessToken(data, userId)
    }

    fun issueRefreshToken(userId: Long): Token {
        val data = mapOf("userId" to userId)
        return tokenHelperIfs.issueRefreshToken(data, userId)
    }

    fun validateToken(token: String): Long {
        return tokenHelperIfs.validateToken(token).toLong()
    }
}