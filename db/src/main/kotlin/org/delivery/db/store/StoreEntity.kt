package org.delivery.db.store

import jakarta.persistence.*
import org.delivery.db.BaseEntity
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import java.math.BigDecimal

@Entity
@Table(name = "store")
class StoreEntity(
    @field:Column(length = 50, nullable = false)
    var name: String,

    @field:Column(length = 150, nullable = false)
    var address: String,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: StoreStatus,

    @field:Enumerated(EnumType.STRING)
    @field:Column(length = 50, nullable = false)
    var category: StoreCategory,

    var star: Double,

    @field:Column(length = 200, nullable = false)
    var thumbnailUrl: String,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var minimumAmount: BigDecimal,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var minimumDeliveryAmount: BigDecimal,

    @field:Column(length = 20)
    var phoneNumber: String,
) : BaseEntity()