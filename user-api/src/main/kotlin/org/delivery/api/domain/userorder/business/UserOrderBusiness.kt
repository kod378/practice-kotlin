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
import org.delivery.common.error.UserOrderErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.enums.UserOrderMenuStatus
import org.springframework.transaction.annotation.Transactional

@Business
@Transactional(readOnly = true)
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
    @Transactional
    fun userOrder(user: User, userOrderRequest: UserOrderRequest): UserOrderResponse {

        val store = storeService.getStoreWithThrow(userOrderRequest.storeId)

        val storeMenuEntityList = userOrderRequest.userOrderMenuRequestList.map { storeMenuService.getStoreMenuWithThrow(it.storeMenuId) }

        val totalAmount = storeMenuEntityList.sumOf { it.amount * getQuantityOrThrow(userOrderRequest, it.id).toBigDecimal() }

        val userOrderEntity = userOrderConverter.toEntity(user, store, totalAmount+store.minimumDeliveryAmount)

        // 주문
        val orderedEntity = userOrderService.order(userOrderEntity)

        // 맵핑
        val userOrderMenuEntityList = storeMenuEntityList.map { userOrderMenuConverter.toEntity(orderedEntity, it, getQuantityOrThrow(userOrderRequest, it.id)) }
        userOrderMenuService.order(userOrderMenuEntityList)

        return userOrderConverter.toResponse(orderedEntity)
    }

    fun userOrderAndSendOrder(user: User, userOrderRequest: UserOrderRequest): UserOrderResponse {
        val response = userOrder(user, userOrderRequest)
        userOrderProducer.sendOrder(response.id)
        return response
    }

    private fun getQuantityOrThrow(userOrderRequest: UserOrderRequest, id: Long?): Int {
        return userOrderRequest.userOrderMenuRequestList.find { it.storeMenuId == id }?.quantity
            ?: throw ApiException(UserOrderErrorCode.ORDER_MENU_NOT_FOUND)
    }

    fun current(user: User): List<UserOrderDetailResponse> {
        val currentOrderList = userOrderService.current(user.id)
        return userOrderDetailResponseList(currentOrderList)
    }

    fun history(user: User): List<UserOrderDetailResponse> {
        val currentOrderList = userOrderService.history(user.id)
        return userOrderDetailResponseList(currentOrderList)
    }

    private fun userOrderDetailResponseList(orderList: List<UserOrderEntity>): List<UserOrderDetailResponse> {
        // 주문 1건씩 처리
        val userOrderDetailResponseList = orderList.map { order ->
            // 사용자가 주문한 메뉴
            val storeMenuList = order.userOrderMenuList
                .filter { it.status == UserOrderMenuStatus.REGISTERED }
            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(order),
                userOrderMenuResponseList = userOrderMenuConverter.toResponse(storeMenuList),
                storeResponse = storeConverter.toResponse(order.store)
            )
        }
        return userOrderDetailResponseList
    }

    fun read(user: User, userOrderId: Long): UserOrderDetailResponse {
        val order = userOrderService.getUserOrderWithoutStatusWithThrow(userOrderId, user.id)

        // 사용자가 주문한 메뉴
        val storeMenuList = order.userOrderMenuList
            .filter { it.status == UserOrderMenuStatus.REGISTERED }

        return UserOrderDetailResponse(
            userOrderResponse = userOrderConverter.toResponse(order),
            userOrderMenuResponseList = userOrderMenuConverter.toResponse(storeMenuList),
            storeResponse =storeConverter.toResponse(order.store)
        )
    }
}