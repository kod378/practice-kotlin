package org.delivery.api.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.delivery.common.log.logger
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class LoggerFilter: Filter {
    val log = logger()

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val cacheRequest = ContentCachingRequestWrapper(request as HttpServletRequest)
        val cacheResponse = ContentCachingResponseWrapper(response as HttpServletResponse)

        // 자꾸 [user-agent : python-requests/2.27.1] 이런식으로 로그가 찍히는데 이걸 막기 위해
        // python-requests는 다 차단
        if (cacheRequest.getHeader("user-agent")?.contains("python-requests") == true) {
            return
        }

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