package org.delivery.api.domain.userorder.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserOrderService(
    private val userOrderRepository: org.delivery.db.userorder.UserOrderRepository
) {

    fun getUserOrderWithThrow(id: Long, userId: Long): org.delivery.db.userorder.UserOrderEntity {
        return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
            ?: throw ApiException(ErrorCode.NULL_POINTER, "주문 정보가 없습니다.")
    }

    fun getUserOrderWithoutStatusWithThrow(id: Long, userId: Long): UserOrderEntity {
        return userOrderRepository.findAllByIdAndUserId(id, userId)
            ?: throw ApiException(ErrorCode.NULL_POINTER, "주문 정보가 없습니다.")
    }

    fun getUserOrders(
        userId: Long
    ): List<UserOrderEntity> {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED)
    }

    fun getUserOrders(
        userId: Long,
        statuses: List<UserOrderStatus>
    ): List<UserOrderEntity> {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statuses)
    }

    // 현재 진행중인 내역
    fun current(userId: Long): List<UserOrderEntity> {
        return getUserOrders(
            userId,
            listOf(
                UserOrderStatus.REGISTERED,
                UserOrderStatus.ORDER,
                UserOrderStatus.ACCEPT,
                UserOrderStatus.COOKING,
                UserOrderStatus.DELIVERY,
            )
        )
    }

    // 과거 주문한 내역
    fun history(userId: Long): List<UserOrderEntity> {
        return getUserOrders(userId, listOf(UserOrderStatus.RECEIVE))
    }

    // 주문 (create)
    fun order(
        userOrderEntity: UserOrderEntity
    ): UserOrderEntity {
        userOrderEntity.status = UserOrderStatus.ORDER
        userOrderEntity.orderedAt = LocalDateTime.now()
        return userOrderRepository.save(userOrderEntity)
    }

    // 주문 확인
    fun accept(userOrderEntity: UserOrderEntity): UserOrderEntity {
        userOrderEntity.acceptAt = LocalDateTime.now()
        return changeStatus(userOrderEntity, UserOrderStatus.ACCEPT)
    }
    // 조리 시작
    fun cooking(userOrderEntity: UserOrderEntity): UserOrderEntity {
        userOrderEntity.cookingStartedAt = LocalDateTime.now()
        return changeStatus(userOrderEntity, UserOrderStatus.COOKING)
    }

    // 배달 시작
    fun delivery(userOrderEntity: UserOrderEntity): UserOrderEntity {
        userOrderEntity.deliveryStartedAt = LocalDateTime.now()
        return changeStatus(userOrderEntity, UserOrderStatus.DELIVERY)
    }

    // 배달 완료
    fun receive(userOrderEntity: UserOrderEntity): UserOrderEntity {
        userOrderEntity.receivedAt = LocalDateTime.now()
        return changeStatus(userOrderEntity, UserOrderStatus.RECEIVE)
    }

    // 상태 변경
    private fun changeStatus(
        userOrderEntity: UserOrderEntity,
        status: UserOrderStatus
    ): UserOrderEntity {
        userOrderEntity.status = status
        return userOrderRepository.save(userOrderEntity)
    }
}