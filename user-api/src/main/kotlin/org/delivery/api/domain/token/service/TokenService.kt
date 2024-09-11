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
        return tokenHelperIfs.issueAccessToken(data)
    }

    fun issueRefreshToken(userId: Long): Token {
        val data = mapOf("userId" to userId)
        return tokenHelperIfs.issueRefreshToken(data)
    }

    fun validateToken(token: String): Long {
        val map = tokenHelperIfs.validateToken(token) //Map<String, Any>
        println("validateToken map : $map")
        val userId = map["userId"] as? Int //jwt parser가 반환하는 Map에 java.lang.Integer로 들어가 있음
            ?: throw ApiException(ErrorCode.NULL_POINTER, "userId is null")
        return userId.toLong()
    }
}