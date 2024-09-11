package org.delivery.api.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.delivery.common.error.TokenErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.common.log.logger
import org.delivery.api.domain.token.business.TokenBusiness
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

@Component
class AuthorizationInterceptor(
    private val tokenBusiness: TokenBusiness
) : HandlerInterceptor {

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
        val accessToken = request.getHeader("Authorization") // "Bearer {token}"
        accessToken?: throw ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND)

        val pureAccessToken = accessToken.replace("Bearer ", "")
        val userId = tokenBusiness.validateAccessToken(pureAccessToken)
        val requestContext = RequestContextHolder.getRequestAttributes()
        requestContext?.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST)
        return true
    }
}