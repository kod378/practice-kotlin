package org.delivery.storeadmin.domain.storemenu.converter

import org.delivery.common.annotation.Converter
import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.delivery.storeadmin.domain.storemenu.model.StoreMenuRegisterRequest
import org.delivery.storeadmin.domain.storemenu.model.StoreMenuResponse

@Converter
class StoreMenuConverter {

    fun toResponse(storeMenuEntity: StoreMenuEntity): StoreMenuResponse {
        return StoreMenuResponse(
            id = storeMenuEntity.id!!,
            name = storeMenuEntity.name,
            amount = storeMenuEntity.amount,
            status = storeMenuEntity.status!!,
            thumbnailUrl = storeMenuEntity.thumbnailUrl,
            likeCount = storeMenuEntity.likeCount,
            sequence = storeMenuEntity.sequence
        )
    }

    fun toResponse(storeMenuEntities: List<StoreMenuEntity>): List<StoreMenuResponse> {
        return storeMenuEntities.map { toResponse(it) }
    }

    fun toEntity(store: StoreEntity, request: StoreMenuRegisterRequest): StoreMenuEntity {
        return StoreMenuEntity(
            store = store,
            name = request.name,
            amount = request.amount,
            thumbnailUrl = request.thumbnailUrl,
            status = StoreMenuStatus.REGISTERED,
            likeCount = 0,
            sequence = 0
        )
    }
}