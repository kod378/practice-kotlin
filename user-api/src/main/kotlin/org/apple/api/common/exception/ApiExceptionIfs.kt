package org.apple.api.common.exception

import org.apple.api.common.error.ErrorCodeIfs

interface ApiExceptionIfs {
    val errorCodeIfs: ErrorCodeIfs
    val errorDescription: String
}