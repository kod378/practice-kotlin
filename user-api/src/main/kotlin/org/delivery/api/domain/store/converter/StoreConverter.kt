package org.delivery.api.domain.store.converter

import org.delivery.api.domain.store.model.StoreInfoResponse
import org.delivery.common.annotation.Converter
import org.delivery.common.error.StoreErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.api.domain.storemenu.model.StoreMenuResponse
import org.delivery.db.store.StoreEntity

@Converter
class StoreConverter {

    fun toResponse(storeEntity: StoreEntity): StoreResponse {
        return StoreResponse(
            id = storeEntity.id?: throw ApiException(StoreErrorCode.STORE_NOT_FOUND),
            name = storeEntity.name,
            address = storeEntity.address,
            category = storeEntity.category,
            star = storeEntity.star,
            thumbnailUrl = storeEntity.thumbnailUrl,
            minimumAmount = storeEntity.minimumAmount,
            minimumDeliveryAmount = storeEntity.minimumDeliveryAmount,
            phoneNumber = storeEntity.phoneNumber
        )
    }

    fun toInfoResponse(store: StoreEntity, menus: List<StoreMenuResponse>): StoreInfoResponse {
        return StoreInfoResponse(
            id = store.id?: throw ApiException(StoreErrorCode.STORE_NOT_FOUND),
            name = store.name,
            address = store.address,
            category = store.category,
            star = store.star,
            thumbnailUrl = store.thumbnailUrl,
            minimumAmount = store.minimumAmount,
            minimumDeliveryAmount = store.minimumDeliveryAmount,
            phoneNumber = store.phoneNumber,
            menus = menus
        )
    }
}