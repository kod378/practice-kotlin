package org.apple.api.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apple.api.common.log.logger
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

@Component
class AuthorizationInterceptor : HandlerInterceptor {

    val log = logger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info("Authorization Interceptor url : ${request.requestURI}")

        // WEB,chrome 의 경우 GET, POST, OPTIONS = pass
        if (HttpMethod.OPTIONS.matches(request.method)) {
            return true
        }

        // .js .html .png .css 등의 정적 리소스 요청은 pass
        if(handler is ResourceHttpRequestHandler) {
            return true
        }

        //TODO: header 검증 로직 추가

        return true //일단 pass
    }
}