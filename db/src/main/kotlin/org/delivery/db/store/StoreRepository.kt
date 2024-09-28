package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository: JpaRepository<StoreEntity, Long> {

//    // 유효한 스토어
//    fun findByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): StoreEntity?

    // 유효한 스토어
    fun findByIdAndStatusNot(id: Long?, status: StoreStatus?): StoreEntity?

//    // 유효한 스토어 리스트
//    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>?

    // 유요한 스토어 리스트
    fun findAllByStatusNotOrderByIdDesc(status: StoreStatus?): List<StoreEntity>?

//    // 유효한 특정 카테고리의 스토어 리스트
//    fun findALlByStatusAndCategoryOrderByStarDesc(status: StoreStatus?, category: StoreCategory?): List<StoreEntity>?

    // 유요한 특정 카테고리의 스토어 리스트
    // select * from store where status != ? and category = ? order by status, star desc
    fun findAllByStatusNotAndCategoryOrderByStatusOrderAscStarDesc(status: StoreStatus?, category: StoreCategory?): List<StoreEntity>?

    // select * from store where name = ? and status = ? order by id desc limit 1
    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity?
}