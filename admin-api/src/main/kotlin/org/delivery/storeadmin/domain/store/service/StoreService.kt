package org.delivery.storeadmin.domain.store.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.db.store.StoreRepository
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreService(
    private val storeRepository: StoreRepository
) {

    // 유효한 스토어 가져오기
    fun getStoreWithThrow(id: Long): StoreEntity {
        val storeEntity = storeRepository.findByIdAndStatusNot(id, StoreStatus.UNREGISTERED)
        return storeEntity ?: throw ApiException(ErrorCode.NULL_POINTER, "등록된 가게가 없습니다.")
    }

    // 스토어 등록
    fun register(storeEntity: StoreEntity): StoreEntity {
        return storeRepository.save(storeEntity)
    }

    // 스토어 상태 변경
    @Transactional
    fun changeStatus(storeId: Long, status: StoreStatus): StoreEntity {
        val storeEntity = getStoreWithThrow(storeId)
        storeEntity.status = status

        if (status == StoreStatus.OPEN) {
            storeEntity.statusOrder = 1
        } else {
            storeEntity.statusOrder = 2
        }

        return storeEntity
    }
}