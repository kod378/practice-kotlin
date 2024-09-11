package org.delivery.storeadmin.domain.store.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.db.store.StoreRepository
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: StoreRepository
) {

    // 유효한 스토어 가져오기
    fun getStoreWithThrow(id: Long): StoreEntity {
        val storeEntity = storeRepository.findByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
        return storeEntity ?: throw ApiException(ErrorCode.NULL_POINTER, "등록된 가게가 없습니다.")
    }

    // 스토어 등록
    fun register(storeEntity: StoreEntity): StoreEntity {
        return storeRepository.save(storeEntity)
    }
}