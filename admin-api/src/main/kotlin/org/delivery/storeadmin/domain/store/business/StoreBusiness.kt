package org.delivery.storeadmin.domain.store.business

import org.delivery.common.annotation.Business
import org.delivery.common.error.StoreErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.enums.StoreStatus
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.store.converter.StoreConverter
import org.delivery.storeadmin.domain.store.model.StoreRegisterRequest
import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.delivery.storeadmin.domain.store.service.StoreService
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService
import org.springframework.transaction.annotation.Transactional

@Business
class StoreBusiness(
    private val storeService: StoreService,
    private val storeConverter: StoreConverter,
    private val storeUserService: StoreUserService,
    private val storeMenuService: StoreMenuService
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

    @Transactional
    fun openStore(user: UserSession): StoreResponse {
        val storeId = user.storeResponse?.id ?: throw ApiException(StoreErrorCode.STORE_NOT_FOUND)
        // if menu is empty, throw exception
        val storeMenus = storeMenuService.getStoreMenusByStoreId(storeId)
        if (storeMenus.isEmpty()) {
            throw ApiException(StoreErrorCode.STORE_MENU_EMPTY)
        }
        val openStore = storeService.changeStatus(storeId, StoreStatus.OPEN)
        return storeConverter.toResponse(openStore)
    }

    @Transactional
    fun closeStore(user: UserSession): StoreResponse {
        val storeId = user.storeResponse?.id ?: throw ApiException(StoreErrorCode.STORE_NOT_FOUND)
        val closeStore = storeService.changeStatus(storeId, StoreStatus.CLOSE)
        return storeConverter.toResponse(closeStore)
    }
}