package org.delivery.common.error

/**
 * StoreErrorCode
 * - 스토어 관련 에러 코드 3000번대 에러 코드 사용
 */
enum class StoreErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    STORE_NOT_FOUND(404, 3404, "스토어를 찾을 수 없습니다."),
    STORE_MENU_EMPTY(400, 3400, "스토어 메뉴가 없습니다."),
}