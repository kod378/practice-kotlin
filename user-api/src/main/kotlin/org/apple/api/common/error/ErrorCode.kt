package org.apple.api.common.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    override val httpStatusCode: HttpStatus,
    override val errorCode: Int,
    override val description: String,
) : ErrorCodeIfs
{

    OK(HttpStatus.OK, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "잘못된 요청입니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 에러입니다."),
    NULL_POINTER(HttpStatus.INTERNAL_SERVER_ERROR, 512, "NULL POINTER"),
}