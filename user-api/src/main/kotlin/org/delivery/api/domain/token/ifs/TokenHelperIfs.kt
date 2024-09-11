package org.delivery.api.domain.token.ifs

import org.delivery.api.domain.token.model.Token

interface TokenHelperIfs {

    fun issueAccessToken(data: Map<String, Any>): Token
    fun issueRefreshToken(data: Map<String, Any>): Token

    fun validateToken(token: String): Map<String, Any>
}