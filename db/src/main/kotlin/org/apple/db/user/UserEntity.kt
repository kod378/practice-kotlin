package org.apple.db.user

import jakarta.persistence.*
import org.apple.db.Address
import org.apple.db.BaseEntity
import org.apple.db.user.enums.UserStatus

@Entity
@Table(name = "user")
class UserEntity(
    @field:Column(length = 50, nullable = false)
    val name: String,

    @field:Column(length = 100, nullable = false)
    val email: String,

    @field:Column(length = 100, nullable = false)
    val password: String,

    @field:Embedded
    var address: Address?= null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserStatus,
) : BaseEntity()


