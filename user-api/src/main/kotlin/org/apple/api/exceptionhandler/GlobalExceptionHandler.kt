package org.apple.api.exceptionhandler

import org.apple.api.common.api.Api
import org.apple.api.common.error.ErrorCode
import org.apple.api.common.log.logger
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Int.MAX_VALUE) // 가장 마지막에 처리
class GlobalExceptionHandler {
    val log = logger()

    @ExceptionHandler(Exception::class)
    fun globalException(exception: Exception) : ResponseEntity<Api<Any>> {
        log.error("", exception)

        return ResponseEntity(
            Api.ERROR(ErrorCode.SERVER_ERROR),
            ErrorCode.SERVER_ERROR.httpStatusCode
        )
    }
}