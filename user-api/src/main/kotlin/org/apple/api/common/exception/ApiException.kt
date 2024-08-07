package org.apple.api.common.exception

import org.apple.api.common.error.ErrorCodeIfs


class ApiException : RuntimeException, ApiExceptionIfs {
    override val errorCodeIfs: ErrorCodeIfs
    override val errorDescription: String

    constructor(errorCodeIfs: ErrorCodeIfs) : super(errorCodeIfs.description) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.description
    }

    constructor(errorCodeIfs: ErrorCodeIfs, errorDescription: String) : super(errorDescription) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorDescription
    }

    constructor(errorCodeIfs: ErrorCodeIfs, tx: Throwable?) : super(tx) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.description
    }

    constructor(errorCodeIfs: ErrorCodeIfs, tx: Throwable?, errorDescription: String) : super(tx) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorDescription
    }
}