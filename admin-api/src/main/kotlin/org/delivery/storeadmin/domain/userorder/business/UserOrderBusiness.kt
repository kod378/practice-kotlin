package org.delivery.storeadmin.domain.userorder.business

import org.delivery.common.annotation.Business
import org.delivery.common.message.model.UserOrderMessage
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.enums.UserOrderMenuStatus
import org.delivery.db.userorder.enums.UserOrderStatus
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter
import org.delivery.storeadmin.domain.userorder.model.UserOrderDetailResponse
import org.delivery.storeadmin.domain.userorder.service.UserOrderService
import org.delivery.storeadmin.domain.userordermenu.converter.UserOrderMenuConverter
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Business
@Transactional
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val userOrderConverter: UserOrderConverter,
    private val sseConnectionPool: SseConnectionPool,
    private val userOrderMenuService: UserOrderMenuService,
    private val storeMenuService: StoreMenuService,
    private val userOrderMenuConverter: UserOrderMenuConverter
) {

    /**
     * 주문
     * 주문 내역 찾기
     * 스토어 찾기
     * 연결된 세션 찾아서
     * push
     */
    @Transactional(readOnly = true)
    fun pushUserOrder(userOrderMessage: UserOrderMessage) {

        // user order entity
        val userOrder = userOrderService.getUserOrderWithMenuList(userOrderMessage.userOrderId)
            ?: throw RuntimeException("주문을 찾을 수 없습니다. userOrderId=${userOrderMessage.userOrderId}")

        val userConnection = sseConnectionPool.getSession(userOrder.store.id.toString())
            ?: throw RuntimeException("연결된 세션이 없습니다. storeId=${userOrder.store.id}")


        val userOrderResponse = userOrderConverter.toResponse(userOrder)
        val userOrderMenuResponseList = userOrderMenuConverter.toResponse(userOrder.userOrderMenuList)

        // response
        val pushData = UserOrderDetailResponse(
            userOrderResponse = userOrderResponse,
            userOrderMenuResponseList = userOrderMenuResponseList
        )

        // 스토어유저에게 push
        userConnection.sendMessage(pushData, "order")
    }

    fun accept(orderId: Long) {
        val userOrderEntity = userOrderService.getUserOrderOrThrow(orderId)
        userOrderEntity.status = UserOrderStatus.ACCEPT
        userOrderEntity.acceptAt = LocalDateTime.now()
    }

    fun cancel(orderId: Long) {
        val userOrderEntity = userOrderService.getUserOrderOrThrow(orderId)
        userOrderEntity.status = UserOrderStatus.CANCEL
        userOrderEntity.cancelAt = LocalDateTime.now()
    }

    fun cooking(orderId: Long) {
        val userOrderEntity = userOrderService.getUserOrderOrThrow(orderId)
        userOrderEntity.status = UserOrderStatus.COOKING
        userOrderEntity.cookingStartedAt = LocalDateTime.now()
    }

    fun delivery(orderId: Long) {
        val userOrderEntity = userOrderService.getUserOrderOrThrow(orderId)
        userOrderEntity.status = UserOrderStatus.DELIVERY
        userOrderEntity.deliveryStartedAt = LocalDateTime.now()
    }

    fun receive(orderId: Long) {
        val userOrderEntity = userOrderService.getUserOrderOrThrow(orderId)
        userOrderEntity.status = UserOrderStatus.RECEIVE
        userOrderEntity.receivedAt = LocalDateTime.now()
    }

    fun history(user: UserSession): List<UserOrderDetailResponse> {
        val historyUserOrderList = userOrderService.history(user.id)
        return userOrderDetailResponseList(historyUserOrderList)
    }

    private fun userOrderDetailResponseList(userOrderList: List<UserOrderEntity>): List<UserOrderDetailResponse> {
        return userOrderList.map { userOrderEntity ->
            val storeMenuList = userOrderEntity.userOrderMenuList
                .filter { it.status == UserOrderMenuStatus.REGISTERED }
            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
                userOrderMenuResponseList = userOrderMenuConverter.toResponse(storeMenuList)
            )
        }
    }


}