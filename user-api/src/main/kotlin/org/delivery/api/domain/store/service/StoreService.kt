package org.delivery.api.domain.store.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StoreService(
    private val storeRepository: org.delivery.db.store.StoreRepository
) {

    // 유효한 스토어 가져오기
    fun getStoreWithThrow(id: Long): org.delivery.db.store.StoreEntity {
//        val storeEntity = storeRepository.findByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
        val storeEntity = storeRepository.findByIdAndStatusNot(id, StoreStatus.UNREGISTERED)
        return storeEntity ?: throw ApiException(ErrorCode.NULL_POINTER)
    }

    // 카테고리로 스토어 검색
    fun searchByCategory(storeCategory: StoreCategory): List<StoreEntity> {
//        val list = storeRepository.findALlByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, storeCategory)
        val list = storeRepository.findAllByStatusNotAndCategoryOrderByStatusOrderAscStarDesc(StoreStatus.UNREGISTERED, storeCategory)
        return list ?: throw ApiException(ErrorCode.NULL_POINTER)
    }

    // 전체 스토어(유요한 상태)
    fun registerStores(): List<StoreEntity> {
//        val list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED)
        val list = storeRepository.findAllByStatusNotOrderByIdDesc(StoreStatus.UNREGISTERED)
        return list ?: throw ApiException(ErrorCode.NULL_POINTER)
    }
}