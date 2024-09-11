package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository: JpaRepository<org.delivery.db.store.StoreEntity, Long> {

    // 유효한 스토어
    fun findByIdAndStatusOrderByIdDesc(id: Long?, status: org.delivery.db.store.enums.StoreStatus?): org.delivery.db.store.StoreEntity?

    // 유효한 스토어 리스트
    fun findAllByStatusOrderByIdDesc(status: org.delivery.db.store.enums.StoreStatus?): List<org.delivery.db.store.StoreEntity>?

    // 유효한 특정 카테고리의 스토어 리스트
    fun findALlByStatusAndCategoryOrderByStarDesc(status: org.delivery.db.store.enums.StoreStatus?, category: org.delivery.db.store.enums.StoreCategory?): List<org.delivery.db.store.StoreEntity>?

    // select * from store where name = ? and status = ? order by id desc limit 1
    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: org.delivery.db.store.enums.StoreStatus?): org.delivery.db.store.StoreEntity?
}