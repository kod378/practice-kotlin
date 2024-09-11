package org.delivery.api.domain.storemenu.controller

import jakarta.validation.Valid
import org.delivery.common.api.Api
import org.delivery.api.domain.storemenu.business.StoreMenuBusiness
import org.delivery.api.domain.storemenu.model.StoreMenuRegisterRequest
import org.delivery.api.domain.storemenu.model.StoreMenuResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api/store-menu")
class StoreMenuOpenApiController(
    private val storeMenuBusiness: StoreMenuBusiness
) {

    @PostMapping
    fun register(
        @Valid @RequestBody request: StoreMenuRegisterRequest
    ): Api<StoreMenuResponse> {
        val response = storeMenuBusiness.register(request)
        return Api.OK(response)
    }
}