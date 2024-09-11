package org.delivery.common.api

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.ErrorCodeIfs

data class Result(
    val code: Int?=null,
    val message: String?=null,
    val detailDescription: String?=null,
) {
    companion object {
        private val OKResult = Result(ErrorCode.OK.errorCode, ErrorCode.OK.description, "성공")
        fun OK(): Result = OKResult
        fun ERROR(errorCodeIfs: ErrorCodeIfs) = Result(errorCodeIfs.errorCode, errorCodeIfs.description, "에러")
        fun ERROR(errorCodeIfs: ErrorCodeIfs, tx: Throwable) = Result(errorCodeIfs.errorCode, errorCodeIfs.description, tx.localizedMessage)
        fun ERROR(errorCodeIfs: ErrorCodeIfs, detailDescription: String) = Result(errorCodeIfs.errorCode, errorCodeIfs.description, detailDescription)
    }
}
