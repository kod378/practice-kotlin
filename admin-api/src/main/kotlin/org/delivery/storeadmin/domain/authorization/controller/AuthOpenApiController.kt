package org.delivery.storeadmin.domain.authorization.controller

import jakarta.validation.Valid
import org.delivery.common.api.Api
import org.delivery.storeadmin.domain.authorization.model.LoginRequest
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.storeuser.business.StoreUserBusiness
import org.delivery.storeadmin.domain.authorization.model.StoreUserRegisterRequest
import org.delivery.storeadmin.domain.token.business.TokenBusiness
import org.delivery.storeadmin.domain.token.model.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/open-api/auth")
class AuthOpenApiController(
    private val authenticationManager: AuthenticationManager,
    private val tokenBusiness: TokenBusiness,
    private val storeUserBusiness: StoreUserBusiness
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): Api<TokenResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        SecurityContextHolder.getContext().authentication = authentication

        val tokenResponse = tokenBusiness.issueToken(request.username, (authentication.principal as UserSession))

        return Api.OK(tokenResponse)
    }

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody request: StoreUserRegisterRequest
    ): Api<TokenResponse> {
        val response = storeUserBusiness.register(request)
        val tokenResponse = tokenBusiness.issueToken(response.storeUser.email, response)

        return Api.OK(tokenResponse)
    }
}