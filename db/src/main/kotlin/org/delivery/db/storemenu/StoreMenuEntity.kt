package org.delivery.db.storemenu

import jakarta.persistence.*
import org.delivery.db.BaseEntity
import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.enums.StoreMenuStatus
import java.math.BigDecimal


@Entity
@Table(name = "store_menu")
class StoreMenuEntity(

    @field:JoinColumn(name = "store_id", nullable = false)
    @field:ManyToOne(fetch = FetchType.LAZY)
    var store: StoreEntity,

    @field:Column(length = 100, nullable = false)
    var name: String,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: StoreMenuStatus,

    @field:Column(length = 200, nullable = false)
    var thumbnailUrl: String,

    var likeCount: Int,

    var sequence: Int
): BaseEntity()