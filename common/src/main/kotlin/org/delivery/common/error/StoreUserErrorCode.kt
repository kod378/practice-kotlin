package org.delivery.common.error

/**
 * StoreUserErrorCode
 * - 스토어 유저 관련 에러 코드 4000번대 에러 코드 사용
 */
enum class StoreUserErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    DUPLICATE_USER(400, 4400, "이미 존재하는 사용자입니다."),
    NOT_FOUND_USER(404, 4404, "스토어 사용자를 찾을 수 없습니다."),
}