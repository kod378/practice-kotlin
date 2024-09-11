package org.delivery.storeadmin.domain.token.ifs

import org.delivery.storeadmin.domain.token.model.Token

interface TokenHelperIfs {

    fun issueAccessToken(data: Map<String, Any>, username: String): Token
    fun issueRefreshToken(data: Map<String, Any>, username: String): Token

    fun getUsernameFromToken(token: String): String
}