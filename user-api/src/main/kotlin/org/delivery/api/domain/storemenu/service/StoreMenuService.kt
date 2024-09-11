package org.delivery.api.domain.storemenu.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.springframework.stereotype.Service

@Service
class StoreMenuService(
    private val storeMenuRepository: org.delivery.db.storemenu.StoreMenuRepository,
) {

    fun register(storeMenuEntity: org.delivery.db.storemenu.StoreMenuEntity): org.delivery.db.storemenu.StoreMenuEntity {
        return storeMenuRepository.save(storeMenuEntity)
    }

    fun getStoreMenusByStoreId(storeId: Long): List<org.delivery.db.storemenu.StoreMenuEntity> {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, org.delivery.db.storemenu.enums.StoreMenuStatus.REGISTERED)
            ?: throw ApiException(ErrorCode.NULL_POINTER)
    }

    fun getStoreMenuWithThrow(id: Long): org.delivery.db.storemenu.StoreMenuEntity {
        val storeMenu = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, org.delivery.db.storemenu.enums.StoreMenuStatus.REGISTERED)
        return storeMenu ?: throw ApiException(ErrorCode.NULL_POINTER)
    }
}