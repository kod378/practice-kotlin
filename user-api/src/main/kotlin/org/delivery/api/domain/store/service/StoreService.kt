package org.delivery.api.domain.store.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: org.delivery.db.store.StoreRepository
) {

    // 유효한 스토어 가져오기
    fun getStoreWithThrow(id: Long): org.delivery.db.store.StoreEntity {
        val storeEntity = storeRepository.findByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
        return storeEntity ?: throw ApiException(ErrorCode.NULL_POINTER)
    }

    // 카테고리로 스토어 검색
    fun searchByCategory(storeCategory: StoreCategory): List<StoreEntity> {
        val list = storeRepository.findALlByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, storeCategory)
        return list ?: throw ApiException(ErrorCode.NULL_POINTER)
    }

    // 전체 스토어(등록상태)
    fun registerStores(): List<StoreEntity> {
        val list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED)
        return list ?: throw ApiException(ErrorCode.NULL_POINTER)
    }
}