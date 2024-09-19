package org.delivery.storeadmin.domain.storemenu.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.StoreMenuErrorCode
import org.delivery.common.exception.ApiException
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
//            ?: throw RuntimeException("StoreMenu not found. id=$id")
            ?: throw ApiException(StoreMenuErrorCode.STORE_MENU_NOT_FOUND)
    }

    fun register(storeMenuEntity: StoreMenuEntity): StoreMenuEntity {
        return storeMenuRepository.save(storeMenuEntity)
    }

    fun getStoreMenusByStoreId(storeId: Long): List<StoreMenuEntity> {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED)
            ?: throw ApiException(ErrorCode.NULL_POINTER)
    }

    fun getStoreMenuWithThrow(id: Long): StoreMenuEntity {
        val storeMenu = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
        return storeMenu ?: throw ApiException(ErrorCode.NULL_POINTER)
    }
}