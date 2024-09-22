package org.delivery.api.domain.store.controller

import org.delivery.common.api.Api
import org.delivery.api.domain.store.business.StoreBusiness
import org.delivery.api.domain.store.model.StoreResponse
import org.delivery.db.store.enums.StoreCategory
import org.springframework.web.bind.annotation.GetMapping
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
}