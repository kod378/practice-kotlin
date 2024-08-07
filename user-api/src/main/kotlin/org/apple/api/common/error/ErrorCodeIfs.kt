package org.apple.api.common.error

import org.springframework.http.HttpStatus

interface ErrorCodeIfs {
    val httpStatusCode: HttpStatus
    val errorCode: Int
    val description: String
}