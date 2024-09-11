package org.delivery.storeadmin.domain.storemenu.service

import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.storemenu.StoreMenuRepository
import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.stereotype.Service

@Service
class StoreMenuService(
    private val storeMenuRepository: StoreMenuRepository,
) {

    fun getStoreMenuWithThrow(id: Long?): StoreMenuEntity {
        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
            ?: throw RuntimeException("StoreMenu not found. id=$id")
    }
}