package org.delivery.db.userordermenu

import org.delivery.db.userorder.enums.UserOrderMenuStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserOrderMenuRepository: JpaRepository<org.delivery.db.userordermenu.UserOrderMenuEntity, Long> {

    // select * from user_order_menu where user_order_id = ? and status = ?
    fun findAllByUserOrderIdAndStatus(userOrderId: Long?, status: UserOrderMenuStatus?): List<org.delivery.db.userordermenu.UserOrderMenuEntity>?
}