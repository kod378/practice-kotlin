package org.delivery.storeadmin.domain.store.converter

import org.delivery.common.annotation.Converter
import org.delivery.common.error.StoreErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.storeadmin.domain.store.model.StoreRegisterRequest
import org.delivery.storeadmin.domain.store.model.StoreResponse

@Converter
class StoreConverter {

    fun toEntity(requestDto: StoreRegisterRequest): StoreEntity {
        return org.delivery.db.store.StoreEntity(
            name = requestDto.name,
            address = requestDto.address,
            category = requestDto.category,
            thumbnailUrl = requestDto.thumbnailUrl,
            minimumAmount = requestDto.minimumAmount,
            minimumDeliveryAmount = requestDto.minimumDeliveryAmount,
            phoneNumber = requestDto.phoneNumber,
            star = 0.0,
            status = org.delivery.db.store.enums.StoreStatus.REGISTERED
        )
    }

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
            phoneNumber = storeEntity.phoneNumber,
            status = storeEntity.status
        )
    }
}