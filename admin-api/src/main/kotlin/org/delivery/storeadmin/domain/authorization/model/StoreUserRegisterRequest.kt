package org.delivery.storeadmin.domain.authorization.model

import jakarta.validation.constraints.NotBlank
import org.delivery.db.storeuser.enums.StoreUserRole

data class StoreUserRegisterRequest(
//    @field:NotBlank
//    val storeName: String,

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String,

//    @field:NotNull
    val role: StoreUserRole = StoreUserRole.USER,
)
