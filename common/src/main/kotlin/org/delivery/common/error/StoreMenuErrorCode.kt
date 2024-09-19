package org.delivery.common.error
/**
 * StoreMenuErrorCode
 * - 스토어 메뉴 관련 에러 코드 5000번대 에러 코드 사용
 */
enum class StoreMenuErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    STORE_MENU_NOT_FOUND(404, 5404, "스토어 메뉴를 찾을 수 없습니다.")
}