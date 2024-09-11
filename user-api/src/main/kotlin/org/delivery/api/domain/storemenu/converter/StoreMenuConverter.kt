package org.delivery.api.domain.storemenu.converter

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.storemenu.model.StoreMenuRegisterRequest
import org.delivery.api.domain.storemenu.model.StoreMenuResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.StoreMenuEntity

@Converter
class StoreMenuConverter {

    fun toEntity(
        store: StoreEntity,
        request: StoreMenuRegisterRequest): StoreMenuEntity {
        return org.delivery.db.storemenu.StoreMenuEntity(
            store = store,
            name = request.name,
            amount = request.amount,
            thumbnailUrl = request.thumbnailUrl,

            status = org.delivery.db.storemenu.enums.StoreMenuStatus.REGISTERED,
            likeCount = 0,
            sequence = 0
        )
    }

    fun toResponse(storeMenuEntity: StoreMenuEntity): StoreMenuResponse {
        return StoreMenuResponse(
            id = storeMenuEntity.id?:throw ApiException(ErrorCode.NULL_POINTER),
            storeId = storeMenuEntity.store.id!!,
            name = storeMenuEntity.name,
            amount = storeMenuEntity.amount,
            status = storeMenuEntity.status,
            thumbnailUrl = storeMenuEntity.thumbnailUrl,
            likeCount = storeMenuEntity.likeCount,
            sequence = storeMenuEntity.sequence
        )
    }

    fun toResponse(storeMenuEntity: List<StoreMenuEntity>): List<StoreMenuResponse> {
        return storeMenuEntity.map { toResponse(it) }
    }
}