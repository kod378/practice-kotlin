package org.delivery.db.storeuser

import jakarta.persistence.*
import org.delivery.db.BaseEntity
import org.delivery.db.storeuser.enums.StoreUserRole
import org.delivery.db.storeuser.enums.StoreUserStatus
import java.time.LocalDateTime

@Entity
@Table(name = "store_user")
class StoreUserEntity(
    @field:Column
    var storeId: Long? = null,

    @field:Column(length = 100, nullable = false, unique = true)
    var email: String,

    @field:Column(length = 100, nullable = false)
    var password: String,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: StoreUserStatus = StoreUserStatus.REGISTERED,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var role: StoreUserRole,

    var registeredAt: LocalDateTime? = null,

    var unregisteredAt: LocalDateTime? = null,

    var lastLoginAt: LocalDateTime? = null,
): BaseEntity()