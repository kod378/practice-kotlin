package org.delivery.api.domain.user.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRegisterRequest(
    @field:NotBlank(message = "이름은 필수 입력값입니다.")
    val name: String,
    @field:NotBlank(message = "이메일은 필수 입력값입니다.")
    @field:Email
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    val password: String,
)
