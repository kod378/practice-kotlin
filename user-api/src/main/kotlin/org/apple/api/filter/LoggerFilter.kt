package org.apple.api.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apple.api.common.log.logger
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class LoggerFilter: Filter {
    val log = logger()

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val cacheRequest = ContentCachingRequestWrapper(request as HttpServletRequest)
        val cacheResponse = ContentCachingResponseWrapper(response as HttpServletResponse)

        chain?.doFilter(cacheRequest, cacheResponse)

        val headerNames = cacheRequest.headerNames
        val headerValues = StringBuilder()

        headerNames.asIterator().forEachRemaining { headerName ->
            val headerValue = cacheRequest.getHeader(headerName)

            // authorization-token : ??? , user-agent : ??? ,
            headerValues.append("[$headerName : $headerValue] ")
        }

        val requestBody = String(cacheRequest.contentAsByteArray)
        val uri = cacheRequest.requestURI
        val method = cacheRequest.method

        log.info(">>>>>> uri : $uri , method: $method , header: $headerValues , body : $requestBody")

        // response
        val responseHeaderValues = StringBuilder()

        cacheResponse.headerNames.forEach { headerName ->
            val headerValue = cacheResponse.getHeader(headerName)

            responseHeaderValues.append("[$headerName : $headerValue] ")
        }

        val responseBody = String(cacheResponse.contentAsByteArray)

        log.info("<<<<<< uri : $uri , method: $method , header: $responseHeaderValues , body : $responseBody")

        // required copy body to response
        cacheResponse.copyBodyToResponse()
    }
}