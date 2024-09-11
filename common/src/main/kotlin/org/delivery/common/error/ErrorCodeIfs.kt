package org.delivery.common.error

interface ErrorCodeIfs {
    val httpStatusCode: Int
    val errorCode: Int
    val description: String
}