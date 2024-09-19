package org.delivery.storeadmin.domain.store.business

import org.delivery.common.annotation.Business
import org.delivery.storeadmin.domain.store.converter.StoreConverter
import org.delivery.storeadmin.domain.store.model.StoreRegisterRequest
import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.delivery.storeadmin.domain.store.service.StoreService
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService
import org.springframework.transaction.annotation.Transactional

@Business
class StoreBusiness(
    private val storeService: StoreService,
    private val storeConverter: StoreConverter,
    private val storeUserService: StoreUserService
) {

    @Transactional
    fun register(storeRegisterRequestDto: StoreRegisterRequest, storeUserId: Long): StoreResponse {
        val storeEntity = storeConverter.toEntity(storeRegisterRequestDto)
        val registeredStoreEntity = storeService.register(storeEntity)

        val storeUserEntity = storeUserService.getRegisterUser(storeUserId)
        storeUserEntity.storeId = registeredStoreEntity.id

        return storeConverter.toResponse(registeredStoreEntity)
    }

    fun getStore(id: Long): StoreResponse {
        val storeEntity = storeService.getStoreWithThrow(id)
        return storeConverter.toResponse(storeEntity)
    }
}