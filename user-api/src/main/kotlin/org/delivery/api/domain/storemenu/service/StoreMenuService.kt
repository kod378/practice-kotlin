package org.delivery.api.domain.storemenu.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.stereotype.Service

@Service
class StoreMenuService(
    private val storeMenuRepository: org.delivery.db.storemenu.StoreMenuRepository,
) {

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