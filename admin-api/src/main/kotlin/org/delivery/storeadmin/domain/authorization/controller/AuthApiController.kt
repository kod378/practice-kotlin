package org.delivery.storeadmin.domain.authorization.controller

import org.delivery.common.api.Api
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.storeuser.business.StoreUserBusiness
import org.delivery.storeadmin.domain.token.business.TokenBusiness
import org.delivery.storeadmin.domain.token.model.TokenResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthApiController(
    private val tokenBusiness: TokenBusiness,
    private val storeUserBusiness: StoreUserBusiness
) {

    @GetMapping("/me")
    fun me(
        @AuthenticationPrincipal user: UserSession
    ): Api<UserSession> {
        println("me: $user")
        return Api.OK(user)
    }

//    백엔드에서 로그아웃을 만들어야 하나? 프론트엔드에서 JWT를 삭제하는 것으로 충분한 것 같다.
//    @PostMapping("/api/logout")
//    fun logout(): Api<Unit> {
//        return Api.OK(Unit)
//    }

    @PostMapping("/refresh")
    fun refresh(
        @AuthenticationPrincipal user: UserSession
    ): Api<TokenResponse> {
        val reTokenResponse = tokenBusiness.issueRefreshToken(user)
        return Api.OK(reTokenResponse)
    }
}