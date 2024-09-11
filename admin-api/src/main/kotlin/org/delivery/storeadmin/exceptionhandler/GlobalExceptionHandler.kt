package org.delivery.storeadmin.exceptionhandler

import org.delivery.common.api.Api
import org.delivery.common.error.ErrorCode
import org.delivery.common.log.logger
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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
            HttpStatusCode.valueOf(ErrorCode.SERVER_ERROR.httpStatusCode)
        )
    }

    fun validationException(exception: MethodArgumentNotValidException) : ResponseEntity<Api<Any>> {
        log.error("", exception)

        return ResponseEntity(
            Api.ERROR(ErrorCode.INVALID_PARAMETER),
            HttpStatus.BAD_REQUEST
        )
    }

}