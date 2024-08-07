package org.apple.api.domain.user.dto

import org.apple.db.Address
import org.apple.db.user.enums.UserStatus

data class UserResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val address: Address?,
    val status: UserStatus,
)