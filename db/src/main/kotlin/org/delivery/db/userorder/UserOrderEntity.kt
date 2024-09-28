package org.delivery.db.userorder

import jakarta.persistence.*
import org.delivery.db.BaseEntity
import org.delivery.db.store.StoreEntity
import org.delivery.db.userorder.enums.UserOrderStatus
import org.delivery.db.userordermenu.UserOrderMenuEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "user_order")
class UserOrderEntity(
    @field:Column(nullable = false)
    var userId: Long,   //user table 1:n

    @field:JoinColumn(name = "store_id", nullable = false)
    @field:ManyToOne(fetch = FetchType.LAZY)
    var store: StoreEntity,  //store table n:1

    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderStatus = UserOrderStatus.REGISTERED,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal,

    @field:Column(nullable = false, length = 30)
    var orderNumber: String = "",

    var orderedAt: LocalDateTime = LocalDateTime.now(),

    var acceptAt: LocalDateTime? = null,

    var cookingStartedAt: LocalDateTime? = null,

    var deliveryStartedAt: LocalDateTime? = null,

    var receivedAt: LocalDateTime? = null,

    @field:OneToMany(mappedBy = "userOrder", fetch = FetchType.LAZY)
    var userOrderMenuList: MutableList<UserOrderMenuEntity> = mutableListOf(),
): BaseEntity()