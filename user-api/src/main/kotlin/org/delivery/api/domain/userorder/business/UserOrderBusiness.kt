package org.delivery.api.domain.userorder.business

import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter
import org.delivery.api.domain.storemenu.service.StoreMenuService
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userorder.converter.UserOrderConverter
import org.delivery.api.domain.userorder.model.UserOrderDetailResponse
import org.delivery.api.domain.userorder.model.UserOrderRequest
import org.delivery.api.domain.userorder.model.UserOrderResponse
import org.delivery.api.domain.userorder.producer.UserOrderProducer
import org.delivery.api.domain.userorder.service.UserOrderService
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService
import org.delivery.common.annotation.Business
import org.delivery.common.log.logger
import org.delivery.db.userorder.enums.UserOrderMenuStatus

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val storeMenuService: StoreMenuService,
    private val userOrderConverter: UserOrderConverter,
    private val userOrderMenuConverter: UserOrderMenuConverter,
    private val userOrderMenuService: UserOrderMenuService,
    private val storeService: StoreService,
    private val storeMenuConverter: StoreMenuConverter,
    private val storeConverter: StoreConverter,
    private val userOrderProducer: UserOrderProducer
) {

    // 1. 사용자, 메뉴 id
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    fun userOrder(user: User, userOrderRequest: UserOrderRequest): UserOrderResponse {

        val store = storeService.getStoreWithThrow(userOrderRequest.storeId)

        val storeMenuEntityList = userOrderRequest.storeMenuIds.map { storeMenuService.getStoreMenuWithThrow(it) }
        val userOrderEntity = userOrderConverter.toEntity(user, store, storeMenuEntityList)

        // 주문
        val orderedEntity = userOrderService.order(userOrderEntity)

        // 맵핑
        val userOrderMenuEntityList = storeMenuEntityList.map { userOrderMenuConverter.toEntity(orderedEntity, it) }

        userOrderMenuService.order(userOrderMenuEntityList)

        // 비동기로 가맹점에 주문 알리기
        userOrderProducer.sendOrder(orderedEntity)

        return userOrderConverter.toResponse(orderedEntity)
    }

    fun current(user: User): List<UserOrderDetailResponse> {
        val currentOrderList = userOrderService.current(user.id)

        // 주문 1건씩 처리
        val userOrderDetailResponseList = currentOrderList.map { order ->

            // 사용자가 주문한 메뉴
            val storeMenuList = order.userOrderMenuList
                .filter { it.status == UserOrderMenuStatus.REGISTERED }
                .map { it.storeMenu }

            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(order),
                storeMenuResponseList = storeMenuConverter.toResponse(storeMenuList),
                storeResponse =storeConverter.toResponse(order.store)
            )
        }

        return userOrderDetailResponseList
    }

    fun history(user: User): List<UserOrderDetailResponse> {
        val currentOrderList = userOrderService.history(user.id)

        // 주문 1건씩 처리
        val userOrderDetailResponseList = currentOrderList.map { order ->

            // 사용자가 주문한 메뉴
            val storeMenuList = order.userOrderMenuList
                .filter { it.status == UserOrderMenuStatus.REGISTERED }
                .map { it.storeMenu }

            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(order),
                storeMenuResponseList = storeMenuConverter.toResponse(storeMenuList),
                storeResponse =storeConverter.toResponse(order.store)
            )
        }

        return userOrderDetailResponseList
    }

    fun read(user: User, userOrderId: Long): UserOrderDetailResponse {
        val order = userOrderService.getUserOrderWithoutStatusWithThrow(userOrderId, user.id)

        // 사용자가 주문한 메뉴
        val storeMenuList = order.userOrderMenuList
            .filter { it.status == UserOrderMenuStatus.REGISTERED }
            .map { it.storeMenu }

        return UserOrderDetailResponse(
            userOrderResponse = userOrderConverter.toResponse(order),
            storeMenuResponseList = storeMenuConverter.toResponse(storeMenuList),
            storeResponse =storeConverter.toResponse(order.store)
        )
    }
}