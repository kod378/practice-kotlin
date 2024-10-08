package org.delivery.db.userorder

import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserOrderRepository: JpaRepository<UserOrderEntity, Long> {

    // select * from user_order where id = ?
    fun findUserOrderById(id: Long?): UserOrderEntity?

    @Query("SELECT DISTINCT uo FROM UserOrderEntity uo " +
            "JOIN FETCH uo.userOrderMenuList uom " +
            "JOIN FETCH uom.storeMenu " +
            "WHERE uo.id = :orderId")
    fun findUserOrderWithMenuListById(@Param("orderId") orderId: Long?): UserOrderEntity?

    // 특정 유저의 모든 주문
    // select * from user_order where user_id = ? and status = ? order by id desc
    fun findAllByUserIdAndStatusOrderByIdDesc(userId: Long?, status: UserOrderStatus?): List<UserOrderEntity>

    // select * from user_order where user_id = ? and status in (?) order by id desc
    fun findAllByUserIdAndStatusInOrderByIdDesc(userId: Long?, status: List<UserOrderStatus>): List<UserOrderEntity>

    // 특정 주문
    // select * from user_order where id = ? and status = ? and user_id = ?
    fun findAllByIdAndStatusAndUserId(id: Long?, status: UserOrderStatus?, userId: Long?): UserOrderEntity?

    fun findAllByIdAndUserId(id: Long?, userId: Long?): UserOrderEntity?

    fun findUserOrdersByStoreIdAndStatusIn(
        orderId: Long,
        orderStatusList: List<UserOrderStatus>
    ): List<UserOrderEntity>

}