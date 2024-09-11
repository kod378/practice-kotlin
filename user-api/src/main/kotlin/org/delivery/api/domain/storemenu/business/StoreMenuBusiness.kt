package org.delivery.api.domain.storemenu.business

import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter
import org.delivery.api.domain.storemenu.model.StoreMenuRegisterRequest
import org.delivery.api.domain.storemenu.model.StoreMenuResponse
import org.delivery.api.domain.storemenu.service.StoreMenuService
import org.delivery.common.annotation.Business

@Business
class StoreMenuBusiness(
    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,
    private val storeService: StoreService
) {

    fun register(request: StoreMenuRegisterRequest): StoreMenuResponse {
        val store = storeService.getStoreWithThrow(request.storeId)
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