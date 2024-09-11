package org.delivery.storeadmin.domain.token.model

import org.delivery.storeadmin.domain.storeuser.ifs.HasStore
import org.delivery.storeadmin.domain.store.model.StoreSimpleResponse
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val accessTokenExpiredAt: LocalDateTime,
    val refreshToken: String,
    val refreshTokenExpiredAt: LocalDateTime,

    override val storeSimpleResponse: StoreSimpleResponse? = null,
): HasStore
