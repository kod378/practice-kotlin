package org.delivery.storeadmin.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.delivery.common.api.Api
import org.delivery.common.exception.ApiException
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: ApiException) {
            val apiResponse = Api.ERROR(e.errorCodeIfs, e.errorDescription)
            val responseBody = objectMapper.writeValueAsBytes(apiResponse)
            response.status = e.errorCodeIfs.httpStatusCode
            response.contentType = "application/json"
            response.outputStream.write(responseBody)
        }
    }
}