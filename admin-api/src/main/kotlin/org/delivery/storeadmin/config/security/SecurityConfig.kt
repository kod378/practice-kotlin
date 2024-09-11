package org.delivery.storeadmin.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.delivery.storeadmin.domain.token.business.TokenBusiness
import org.delivery.storeadmin.filter.ExceptionHandlerFilter
import org.delivery.storeadmin.filter.JwtAuthFilter
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity  // security 활성화
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val tokenBusiness: TokenBusiness,
    private val objectMapper: ObjectMapper
) {

    private val SWAGGER = arrayOf(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**",
    )

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() } // CSRF 보호 비활성화 (개발 단계에서)
            .cors { it.configurationSource(corsConfigurationSource()) }
            .authorizeHttpRequests {
                // resource 에 대해서는 모든 요청 허용
                it.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                // swagger 는 인증 없이 통과
                it.requestMatchers(*SWAGGER).permitAll()

                // open-api/** 는 인증 없이 통과
                it.requestMatchers("/open-api/**").permitAll()

                // spring security 로그인 시 에러가 조금 있는 듯
                it.requestMatchers("/error").permitAll()

                // 나머지는 인증 필요
                it.anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(
                JwtAuthFilter(tokenBusiness, userDetailsService),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                ExceptionHandlerFilter(objectMapper),
                JwtAuthFilter::class.java
            )
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000") // Vue.js 애플리케이션의 URL
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun authenticationManager(http: HttpSecurity, passwordEncoder: PasswordEncoder): AuthenticationManager {
        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        builder.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
        return builder.build()
    }
}