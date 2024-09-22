package org.delivery.common.error

/**
 * UserErrorCode
 * - 사용자 관련 에러 코드 1000번대 에러 코드 사용
 */
enum class UserErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(400, 1405, "이미 사용중인 이메일입니다."),
}