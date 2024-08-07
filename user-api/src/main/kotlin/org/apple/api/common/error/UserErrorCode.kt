package org.apple.api.common.error

import org.springframework.http.HttpStatus

/**
 * UserErrorCode
 * - 사용자 관련 에러 코드 1000번대 에러 코드 사용
 */
enum class UserErrorCode(
    override val httpStatusCode: HttpStatus,
    override val errorCode: Int,
    override val description: String,
) : ErrorCodeIfs
{
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, 1404, "사용자를 찾을 수 없습니다."),
}