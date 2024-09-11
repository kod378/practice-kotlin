package org.delivery.api.domain.token.model

import java.time.LocalDateTime

data class Token(
    val token: String,
    val expiredAt: LocalDateTime
)
