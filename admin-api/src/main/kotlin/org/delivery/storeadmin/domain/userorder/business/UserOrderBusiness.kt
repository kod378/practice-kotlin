package org.delivery.storeadmin.domain.userorder.business

import org.delivery.common.annotation.Business
import org.delivery.common.message.model.UserOrderMessage
import org.delivery.db.userorder.enums.UserOrderStatus
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter
import org.delivery.storeadmin.domain.userorder.model.UserOrderDetailResponse
import org.delivery.storeadmin.domain.userorder.service.UserOrderService
import org.delivery.storeadmin.domain.userordermenu.converter.UserOrderMenuConverter
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService
import org.springframework.transaction.annotation.Transactional

@Business
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
        userConnection.sendMessage(pushData)
    }

    fun accept(orderId: Long) {
        userOrderService.changeStatus(orderId, UserOrderStatus.ACCEPT)
    }

    fun cancel(orderId: Long) {
        userOrderService.changeStatus(orderId, UserOrderStatus.CANCEL)
    }
}