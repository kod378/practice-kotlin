package org.apple.api.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRegisterRequestDto(
    @NotBlank
    val name: String,
    @NotBlank
    @Email
    val email: String,
    @NotBlank
    val password: String,
)
