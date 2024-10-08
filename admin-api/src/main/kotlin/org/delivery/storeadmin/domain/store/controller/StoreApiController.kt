package org.delivery.storeadmin.domain.store.controller

import jakarta.validation.Valid
import org.delivery.common.api.Api
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.store.business.StoreBusiness
import org.delivery.storeadmin.domain.store.model.StoreRegisterRequest
import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store")
class StoreApiController(
    private val storeBusiness: StoreBusiness
) {

    @PostMapping("")
    fun register(
        @AuthenticationPrincipal user: UserSession,
        @Valid @RequestBody storeRegisterRequest: StoreRegisterRequest
    ): Api<StoreResponse> {
        val response = storeBusiness.register(storeRegisterRequest, user.id)
        return Api.OK(response)
    }

    @GetMapping("")
    fun getStores(
        @AuthenticationPrincipal user: UserSession,
    ): Api<StoreResponse?> {
        val response = user.storeResponse?.let { storeBusiness.getStore(it.id) }
        return Api.OK(response)
    }

    @PutMapping("/open")
    fun openStore(
        @AuthenticationPrincipal user: UserSession,
    ): Api<StoreResponse> {
        val response = storeBusiness.openStore(user)
        return Api.OK(response)
    }

    @PutMapping("/close")
    fun closeStore(
        @AuthenticationPrincipal user: UserSession,
    ): Api<StoreResponse> {
        val response = storeBusiness.closeStore(user)
        return Api.OK(response)
    }
}