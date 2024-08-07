package org.apple.api.common.api

import org.apple.api.common.error.ErrorCode
import org.apple.api.common.error.ErrorCodeIfs

data class Result(
    val code: Int,
    val message: String,
    val detailDescription: String,
)
{
    companion object {
        private val OKResult = Result(ErrorCode.OK.errorCode, ErrorCode.OK.description, "성공")
        fun OK(): Result = OKResult
        fun ERROR(errorCodeIfs: ErrorCodeIfs) = Result(errorCodeIfs.errorCode, errorCodeIfs.description, "에러")
        fun ERROR(errorCodeIfs: ErrorCodeIfs, tx: Throwable) = Result(errorCodeIfs.errorCode, errorCodeIfs.description, tx.localizedMessage)
        fun ERROR(errorCodeIfs: ErrorCodeIfs, detailDescription: String) = Result(errorCodeIfs.errorCode, errorCodeIfs.description, detailDescription)
    }
}
