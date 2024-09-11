package org.delivery.common.error

enum class ErrorCode(
    override val httpStatusCode: Int,
    override val errorCode: Int,
    override val description: String
): ErrorCodeIfs {

    OK(200, 200, "성공"),
    BAD_REQUEST(400, 400, "잘못된 요청입니다."),
    SERVER_ERROR(500, 500, "서버 에러입니다."),
    NULL_POINTER(500, 512, "NULL POINTER"),
    INVALID_PARAMETER(400, 441, "잘못된 입력값입니다. 확인해주세요."),
    INVALID_PASSWORD(400, 442, "비밀번호가 일치하지 않습니다."),

}