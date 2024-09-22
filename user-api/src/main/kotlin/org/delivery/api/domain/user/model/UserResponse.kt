package org.delivery.api.domain.user.model

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
//    val address: org.delivery.db.Address?,
    val status: org.delivery.db.user.enums.UserStatus,
)