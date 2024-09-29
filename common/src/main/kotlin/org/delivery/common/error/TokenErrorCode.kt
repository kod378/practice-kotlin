package org.delivery.common.error

/**
 * UserErrorCode
 * - 토큰 관련 에러 코드 2000번대 에러 코드 사용
 */
enum class TokenErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    INVALID_TOKEN(401, 2000, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(401, 2001, "만료된 토큰입니다."),
    TOKEN_EXCEPTION(400, 2002, "알 수 없는 토큰 에러입니다."),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "Authorization 토큰이 존재하지 않습니다."),
    BAD_CREDENTIALS(401, 2004, "잘못된 인증 정보입니다."),
}