package org.delivery.db.userordermenu

import jakarta.persistence.*
import org.delivery.db.BaseEntity
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.enums.UserOrderMenuStatus

@Entity
@Table(name = "user_order_menu")
class UserOrderMenuEntity(

    @field:JoinColumn(name = "user_order_id", nullable = false)
    @field:ManyToOne(fetch = FetchType.LAZY)
    var userOrder: UserOrderEntity,   //n : 1

    @field:JoinColumn(name = "store_menu_id", nullable = false)
    @field:ManyToOne(fetch = FetchType.LAZY)
    var storeMenu: StoreMenuEntity,   //n : 1

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderMenuStatus = UserOrderMenuStatus.REGISTERED,

    var quantity: Int = 1,
): BaseEntity()