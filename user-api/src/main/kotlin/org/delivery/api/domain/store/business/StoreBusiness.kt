package org.delivery.api.domain.store.business

import org.delivery.common.annotation.Business
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.api.domain.store.service.StoreService

@Business
class StoreBusiness(
    private val storeService: StoreService,
    private val storeConverter: StoreConverter
) {

    fun searchCategory(storeCategory: org.delivery.db.store.enums.StoreCategory): List<StoreResponse> {
        val storeList = storeService.searchByCategory(storeCategory)
        return storeList.map { storeConverter.toResponse(it) }
    }
}