package org.delivery.storeadmin.domain.token.service

import org.delivery.storeadmin.domain.token.model.Token
import org.delivery.storeadmin.domain.token.ifs.TokenHelperIfs
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenHelperIfs: TokenHelperIfs
) {

    fun issueAccessToken(username: String): Token {
        val data = mapOf("username" to username)
        return tokenHelperIfs.issueAccessToken(data, username)
    }

    fun issueRefreshToken(username: String): Token {
        val data = mapOf("username" to username)
        return tokenHelperIfs.issueRefreshToken(data, username)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = tokenHelperIfs.getUsernameFromToken(token)
        return userDetails.username == username
    }

    fun getUsernameFromToken(token: String): String {
        return tokenHelperIfs.getUsernameFromToken(token)
    }
}