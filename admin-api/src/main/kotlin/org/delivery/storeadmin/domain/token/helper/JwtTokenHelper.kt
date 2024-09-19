package org.delivery.storeadmin.domain.token.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.delivery.common.error.TokenErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.storeadmin.domain.token.model.Token
import org.delivery.storeadmin.domain.token.ifs.TokenHelperIfs
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

    override fun issueAccessToken(data: Map<String, Any>, username: String): Token {
        return jwtTokenWithPlusHour(data, username, accessTokenPlusHour)
    }

    override fun issueRefreshToken(data: Map<String, Any>, username: String): Token {
//        return jwtTokenWithPlusHour(data, username, refreshTokenPlusHour)
        return jwtTokenWithPlusHour(data, username, 2)
    }

    override fun getUsernameFromToken(token: String): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
        val parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()

        try {
            val result = parser.parseClaimsJws(token)
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

    private fun jwtTokenWithPlusHour(data: Map<String, Any>, subject: String, tokenPlusHour: Long): Token {
        val expiredLocalDateTime = LocalDateTime.now().plusHours(tokenPlusHour)
//        val expiredLocalDateTime = LocalDateTime.now().plusMinutes(tokenPlusHour) //x minutes for testing
//        val expiredLocalDateTime = LocalDateTime.now().plusSeconds(tokenPlusHour) //x second for testing
        val expiredAt = Date.from(
            expiredLocalDateTime.atZone(
                ZoneId.systemDefault()
            ).toInstant()
        )

        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        val jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setSubject(subject)
            .setExpiration(expiredAt)
            .compact()

        return Token(jwtToken, expiredLocalDateTime)
    }
}