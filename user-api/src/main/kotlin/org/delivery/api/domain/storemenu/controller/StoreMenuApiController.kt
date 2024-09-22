package org.delivery.api.domain.storemenu.controller

import org.delivery.common.api.Api
import org.delivery.api.domain.storemenu.business.StoreMenuBusiness
import org.delivery.api.domain.storemenu.model.StoreMenuResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store-menu")
class StoreMenuApiController(
    private val storeMenuBusiness: StoreMenuBusiness
) {

    @GetMapping("/search")
    fun search(
        @RequestParam storeId: Long
    ): Api<List<StoreMenuResponse>> {
        val response = storeMenuBusiness.search(storeId)
        return Api.OK(response)
    }

}