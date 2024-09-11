package org.delivery.api.exceptionhandler

import org.delivery.common.api.Api
import org.delivery.common.exception.ApiException
import org.delivery.common.log.logger
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Int.MIN_VALUE) // 가장 먼저 처리
class ApiExceptionHandler {
    val log = logger()

    @ExceptionHandler(ApiException::class)
    fun apiException(apiException: ApiException) : ResponseEntity<Api<Any>> {
        log.error("", apiException)

        val errorCode = apiException.errorCodeIfs

        return ResponseEntity(
            Api.ERROR(errorCode, apiException.errorDescription),
            HttpStatusCode.valueOf(errorCode.httpStatusCode)
        )
    }
}