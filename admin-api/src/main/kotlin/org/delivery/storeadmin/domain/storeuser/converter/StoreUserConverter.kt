package org.delivery.storeadmin.domain.storeuser.converter

import org.delivery.common.annotation.Converter
import org.delivery.db.store.StoreEntity
import org.delivery.db.storeuser.StoreUserEntity
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.authorization.model.StoreUserRegisterRequest
import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.delivery.storeadmin.domain.store.model.StoreSimpleResponse
import org.delivery.storeadmin.domain.storeuser.model.StoreUserResponse

@Converter
class StoreUserConverter() {

    fun toEntity(request: StoreUserRegisterRequest): StoreUserEntity {
//        val storeName = request.storeName
        return StoreUserEntity(
            email = request.email,
            password = request.password,
            role = request.role,
        )
    }

    fun toResponse(
        storeUserEntity: StoreUserEntity,
        storeEntity: StoreEntity?
    ): StoreUserResponse {
        return StoreUserResponse(
            storeUser = StoreUserResponse.StoreUserResponse(
                id = storeUserEntity.id!!,
                email = storeUserEntity.email,
                status = storeUserEntity.status,
                role = storeUserEntity.role,
                registeredAt = storeUserEntity.registeredAt!!,
                unregisteredAt = storeUserEntity.unregisteredAt,
                lastLoginAt = storeUserEntity.lastLoginAt,
            ),
            storeResponse = storeEntity?.let { StoreResponse(
                id = it.id!!,
                name = it.name,
                address = it.address,
                category = it.category,
                star = it.star,
                thumbnailUrl = it.thumbnailUrl,
                minimumAmount = it.minimumAmount,
                minimumDeliveryAmount = it.minimumDeliveryAmount,
                phoneNumber = it.phoneNumber,
                status = it.status
            ) }
        )
    }

    fun toResponse(userSession: UserSession): StoreUserResponse {
        return StoreUserResponse(
            storeUser = StoreUserResponse.StoreUserResponse(
                id = userSession.id,
                email = userSession.email,
                status = userSession.status,
                role = userSession.role,
                registeredAt = userSession.registeredAt,
                unregisteredAt = userSession.unregisteredAt,
                lastLoginAt = userSession.lastLoginAt,
            ),
            storeResponse = userSession.storeResponse
        )
    }
}