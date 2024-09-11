package org.delivery.api.domain.user.model

import jakarta.validation.constraints.NotBlank

data class UserLoginRequest(
    @NotBlank
    val email: String,
    @NotBlank
    val password: String
)