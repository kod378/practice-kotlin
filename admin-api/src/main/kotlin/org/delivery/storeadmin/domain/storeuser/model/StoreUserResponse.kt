package org.delivery.storeadmin.domain.storeuser.model

import org.delivery.db.storeuser.enums.StoreUserRole
import org.delivery.db.storeuser.enums.StoreUserStatus
import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.delivery.storeadmin.domain.storeuser.ifs.HasStore
import java.time.LocalDateTime

data class StoreUserResponse(
    val storeUser: StoreUserResponse,
    override val storeResponse: StoreResponse? = null,
): HasStore {
    data class StoreUserResponse(
        val id: Long,
        val email: String,
        val status: StoreUserStatus,
        val role: StoreUserRole,
        val registeredAt: LocalDateTime,
        val unregisteredAt: LocalDateTime?,
        val lastLoginAt: LocalDateTime?,
    )
}

