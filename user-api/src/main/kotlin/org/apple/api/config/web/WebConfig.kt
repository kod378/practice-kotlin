package org.apple.api.config.web

import org.apple.api.interceptor.AuthorizationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val authorizationInterceptor: AuthorizationInterceptor
) : WebMvcConfigurer {

    val OPEN_API = listOf(
        "/open-api/**",
    )

    val DEFAULT_EXCLUDE = listOf(
        "/",
        "favicon.ico",
        "/error",
    )

    val SWAGGER = listOf(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**",
    )

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor)
            .excludePathPatterns(OPEN_API)
            .excludePathPatterns(DEFAULT_EXCLUDE)
            .excludePathPatterns(SWAGGER)
    }
}