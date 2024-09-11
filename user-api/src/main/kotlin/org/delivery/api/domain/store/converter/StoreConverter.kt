package org.delivery.api.domain.store.converter

import org.delivery.common.annotation.Converter
import org.delivery.common.error.StoreErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.store.model.StoreResponse

@Converter
class StoreConverter {

    fun toResponse(storeEntity: org.delivery.db.store.StoreEntity): StoreResponse {
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
}