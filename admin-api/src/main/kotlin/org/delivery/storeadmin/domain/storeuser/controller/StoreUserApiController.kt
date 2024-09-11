package org.delivery.storeadmin.domain.storeuser.controller

import io.swagger.v3.oas.annotations.Parameter
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.storeuser.converter.StoreUserConverter
import org.delivery.storeadmin.domain.storeuser.model.StoreUserResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store-user")
class StoreUserApiController(
    private val storeUserConverter: StoreUserConverter
) {

    @GetMapping("/me")
    fun me(
        @Parameter(hidden = true)
        @AuthenticationPrincipal userSession: UserSession
    ): StoreUserResponse {
        return storeUserConverter.toResponse(userSession)
    }
}