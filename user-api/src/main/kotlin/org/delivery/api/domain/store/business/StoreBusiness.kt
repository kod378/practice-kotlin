package org.delivery.api.domain.store.business

import org.delivery.common.annotation.Business
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.model.StoreInfoResponse
import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.storemenu.business.StoreMenuBusiness
import org.delivery.db.store.enums.StoreCategory

@Business
class StoreBusiness(
    private val storeService: StoreService,
    private val storeConverter: StoreConverter,
    private val storeMenuBusiness: StoreMenuBusiness,
) {

    fun searchCategory(storeCategory: StoreCategory): List<StoreResponse> {
        val storeList = storeService.searchByCategory(storeCategory)
        return storeList.map { storeConverter.toResponse(it) }
    }

    fun getInfoWithMenus(storeId: Long): StoreInfoResponse {
        val store = storeService.getStoreWithThrow(storeId)
        val menus = storeMenuBusiness.search(storeId)
        return storeConverter.toInfoResponse(store, menus)
    }
}