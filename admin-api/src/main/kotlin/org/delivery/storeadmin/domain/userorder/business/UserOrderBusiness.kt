package org.delivery.storeadmin.domain.userorder.business

import org.delivery.common.annotation.Business
import org.delivery.common.message.model.UserOrderMessage
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter
import org.delivery.storeadmin.domain.userorder.model.UserOrderDetailResponse
import org.delivery.storeadmin.domain.userorder.service.UserOrderService
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val userOrderConverter: UserOrderConverter,
    private val sseConnectionPool: SseConnectionPool,
    private val userOrderMenuService: UserOrderMenuService,
    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,
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
        val userOrder = userOrderService.getUserOrder(userOrderMessage.userOrderId)
            ?: throw RuntimeException("주문을 찾을 수 없습니다. userOrderId=${userOrderMessage.userOrderId}")

        val userConnection = sseConnectionPool.getSession(userOrder.store.id.toString())
            ?: throw RuntimeException("연결된 세션이 없습니다. storeId=${userOrder.store.id}")

        // user order menu
        val userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrder.id)

        // user order menu -> store menu
        val storeMenuResponseList = userOrderMenuList.map {
            val storeMenuEntity = storeMenuService.getStoreMenuWithThrow(it.storeMenu.id)
            storeMenuConverter.toResponse(storeMenuEntity)
        }

        val userOrderResponse = userOrderConverter.toResponse(userOrder)

        // response
        val pushData = UserOrderDetailResponse(
            userOrderResponse = userOrderResponse,
            storeMenuResponseList = storeMenuResponseList,
        )

        // 사용자에게 push
        userConnection.sendMessage(pushData)
    }
}