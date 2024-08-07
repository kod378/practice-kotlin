package org.apple.api.common.api

import jakarta.validation.Valid
import org.apple.api.common.error.ErrorCodeIfs

data class Api<T>(

    val result: Result,

    @Valid
    val body: T,
)
{
    companion object {
        fun <T> OK(body: T): Api<T> = Api(Result.OK(), body)
        fun ERROR(result: Result) : Api<Any> {
            return Api(result, Any())
        }
        fun ERROR(errorCodeIfs: ErrorCodeIfs) : Api<Any> {
            return Api(Result.ERROR(errorCodeIfs), Any())
        }
        //too dangerous
        fun ERROR(errorCodeIfs: ErrorCodeIfs, tx: Throwable) : Api<Any> {
            return Api(Result.ERROR(errorCodeIfs, tx), Any())
        }
        fun ERROR(errorCodeIfs: ErrorCodeIfs, detailDescription: String) : Api<Any> {
            return Api(Result.ERROR(errorCodeIfs, detailDescription), Any())
        }
    }
}
