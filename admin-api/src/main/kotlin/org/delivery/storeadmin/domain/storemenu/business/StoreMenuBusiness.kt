package org.delivery.storeadmin.domain.storemenu.business

import org.delivery.common.annotation.Business
import org.delivery.common.error.StoreErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.store.service.StoreService
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter
import org.delivery.storeadmin.domain.storemenu.model.StoreMenuRegisterRequest
import org.delivery.storeadmin.domain.storemenu.model.StoreMenuResponse
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService

@Business
class StoreMenuBusiness(
    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,
    private val storeService: StoreService
) {

    fun register(request: StoreMenuRegisterRequest, user: UserSession): StoreMenuResponse {
//        val store = storeService.getStoreWithThrow(request.storeId)
        val store = user.storeResponse?.let { storeService.getStoreWithThrow(it.id) }
            ?: throw ApiException(StoreErrorCode.STORE_NOT_FOUND)

        val entity = storeMenuConverter.toEntity(store, request)
        val savedEntity = storeMenuService.register(entity)
        val response = storeMenuConverter.toResponse(savedEntity)
        return response
    }

    fun search(storeId: Long): List<StoreMenuResponse> {
        val list = storeMenuService.getStoreMenusByStoreId(storeId)
        return list.map { storeMenuConverter.toResponse(it) }
    }
}