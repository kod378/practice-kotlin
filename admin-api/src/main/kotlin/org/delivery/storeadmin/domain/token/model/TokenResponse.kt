package org.delivery.storeadmin.domain.token.model

import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.delivery.storeadmin.domain.storeuser.ifs.HasStore
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val accessTokenExpiredAt: LocalDateTime,
    val refreshToken: String,
    val refreshTokenExpiredAt: LocalDateTime,

    override val storeResponse: StoreResponse? = null,
): HasStore
