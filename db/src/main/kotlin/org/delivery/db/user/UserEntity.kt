package org.delivery.db.user

import jakarta.persistence.*
import org.delivery.db.Address
import org.delivery.db.BaseEntity
import org.delivery.db.user.enums.UserStatus

@Entity
@Table(name = "user")
class UserEntity(
    @field:Column(length = 50, nullable = false)
    var name: String,

    @field:Column(length = 100, nullable = false, unique = true)
    var email: String,

    @field:Column(length = 100, nullable = false)
    var password: String,

    @field:Embedded
    var address: Address?= null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.REGISTERED,
) : BaseEntity()
