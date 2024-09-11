package org.delivery.api.domain.user.model

import java.time.LocalDateTime

data class User(
    val id: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    val name: String,
    val email: String,
    val password: String,
    val address: org.delivery.db.Address?= null,
    val status: org.delivery.db.user.enums.UserStatus,
)
