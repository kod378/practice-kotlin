package org.delivery.api.resolver

import org.delivery.api.common.annotation.UserSession
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.user.service.UserService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserSessionResolver(
    private val userService: UserService
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        // 지원하는 파라미터 체크, 어노테이션 체크

        //1. 어노테이션 체크
        val annotation = parameter.hasParameterAnnotation(UserSession::class.java)

        //2. 파라미터 타입 체크
        val parameterType = parameter.parameterType == User::class.java

        return annotation && parameterType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        // supportsParameter가 true일 때 실행

        // requestContextHolder 에서 찾아오기
        val requestContext = RequestContextHolder.getRequestAttributes()
        val userId = requestContext?.getAttribute("userId", RequestAttributes.SCOPE_REQUEST) as Long
        val userEntity = userService.getUserWithThrow(userId)

        // 사용자 정보 세팅
        return User(
            id = userEntity.id!!,
            name = userEntity.name,
            email = userEntity.email,
            password = userEntity.password,
            address = userEntity.address,
            status = userEntity.status,
            createdAt = userEntity.createdAt!!,
            updatedAt = userEntity.updatedAt!!
        )
    }
}