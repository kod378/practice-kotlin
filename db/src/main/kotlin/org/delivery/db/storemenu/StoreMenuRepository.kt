package org.delivery.db.storemenu

import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreMenuRepository: JpaRepository<org.delivery.db.storemenu.StoreMenuEntity, Long> {

    // 유효한 메뉴체크
    // select * from sotre_menu where id = ? and status = ? order by id desc limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: org.delivery.db.storemenu.enums.StoreMenuStatus?): org.delivery.db.storemenu.StoreMenuEntity?

    // 특정 가게의 메뉴 가져오기
    // select * from store_menu where store_id = ? and status = ? order by sequence desc
    fun findAllByStoreIdAndStatusOrderBySequenceDesc(storeId: Long?, status: org.delivery.db.storemenu.enums.StoreMenuStatus?): List<org.delivery.db.storemenu.StoreMenuEntity>?
}