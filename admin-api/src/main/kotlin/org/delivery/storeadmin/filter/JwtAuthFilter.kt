package org.delivery.storeadmin.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.delivery.common.exception.ApiException
import org.delivery.storeadmin.domain.token.business.TokenBusiness
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val tokenBusiness: TokenBusiness,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getTokenFromRequest(request)

        token?.let {
            val userDetails = getUserDetails(it)
            val isValid = tokenBusiness.validateAccessToken(it, userDetails)
            if (isValid) {
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token != null && token.startsWith("Bearer ")) {
            token.substring(7)
        } else null
    }

    private fun getUserDetails(token: String): UserDetails {
        val username = tokenBusiness.getUsernameFromToken(token)
        return userDetailsService.loadUserByUsername(username)
    }
}