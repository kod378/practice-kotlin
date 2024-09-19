package org.delivery.storeadmin.domain.storemenu.controller

import jakarta.validation.Valid
import org.delivery.common.api.Api
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.storemenu.business.StoreMenuBusiness
import org.delivery.storeadmin.domain.storemenu.model.StoreMenuRegisterRequest
import org.delivery.storeadmin.domain.storemenu.model.StoreMenuResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store-menu")
class StoreMenuApiController(
    private val storeMenuBusiness: StoreMenuBusiness
) {

    @PostMapping
    fun register(
        @AuthenticationPrincipal user: UserSession,
        @Valid @RequestBody request: StoreMenuRegisterRequest
    ): Api<StoreMenuResponse> {
       val response = storeMenuBusiness.register(request, user)
       return Api.OK(response)
    }
}