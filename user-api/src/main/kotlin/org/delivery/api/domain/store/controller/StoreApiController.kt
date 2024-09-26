package org.delivery.api.domain.store.controller

import org.delivery.common.api.Api
import org.delivery.api.domain.store.business.StoreBusiness
import org.delivery.api.domain.store.model.StoreInfoResponse
import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.db.store.enums.StoreCategory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store")
class StoreApiController(
    private val storeBusiness: StoreBusiness
) {

    @GetMapping("/search")
    fun search(
        @RequestParam storeCategory: StoreCategory
    ): Api<List<StoreResponse>> {
        val response = storeBusiness.searchCategory(storeCategory)
        return Api.OK(response)
    }

    // 스토어 정보와 메뉴 정보를 가져오는 API
    @GetMapping("/info/{storeId}")
    fun info(
        @PathVariable storeId: Long,
    ): Api<StoreInfoResponse> {
        val response = storeBusiness.getInfoWithMenus(storeId)
        return Api.OK(response)
    }
}