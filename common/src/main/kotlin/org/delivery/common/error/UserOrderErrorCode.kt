package org.delivery.common.error

/**
 * UserOrderErrorCode
 * - 사용자 주문 관련 에러 코드 6000번대 에러 코드 사용
 */
enum class UserOrderErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    ORDER_MENU_NOT_FOUND(404, 6404, "주문 메뉴를 찾을 수 없습니다."),
}