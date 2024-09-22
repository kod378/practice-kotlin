package org.delivery.api.domain.token.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.delivery.common.error.TokenErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.token.model.Token
import org.delivery.api.domain.token.ifs.TokenHelperIfs
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class JwtTokenHelper(
    @Value("\${token.secret.key}")
    val secretKey: String,
    @Value("\${token.access-token.plus-hour}")
    val accessTokenPlusHour: Long,
    @Value("\${token.refresh-token.plus-hour}")
    val refreshTokenPlusHour: Long,
): TokenHelperIfs {

    override fun issueAccessToken(data: Map<String, Any>, userId: Long): Token {
        return jwtTokenWithPlusHour(data, userId, accessTokenPlusHour)
    }

    override fun issueRefreshToken(data: Map<String, Any>, userId: Long): Token {
        return jwtTokenWithPlusHour(data, userId, refreshTokenPlusHour)
    }

    override fun validateToken(token: String): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
        val parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()

        try {
            val result = parser.parseClaimsJws(token)
//            return result.body
            return result.body.subject
        } catch (e: Exception) {
            when (e) {
                //토큰이 유효하지 않음
                is SignatureException -> {
                    throw ApiException(TokenErrorCode.INVALID_TOKEN, e)
                }
                //토큰이 만료됨
                is ExpiredJwtException -> {
                    throw ApiException(TokenErrorCode.EXPIRED_TOKEN, e)
                }
                //그 외 토큰 에러
                else -> {
                    throw ApiException(TokenErrorCode.INVALID_TOKEN, e)
                }
            }
        }
    }

    private fun jwtTokenWithPlusHour(data: Map<String, Any>, userId:Long, tokenPlusHour: Long): Token {
        val expiredLocalDateTime = LocalDateTime.now().plusHours(tokenPlusHour)
        val expiredAt = Date.from(
            expiredLocalDateTime.atZone(
                ZoneId.systemDefault()
            ).toInstant()
        )

        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        val jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setSubject(userId.toString())
            .setExpiration(expiredAt)
            .compact()

        return Token(jwtToken, expiredLocalDateTime)
    }
}