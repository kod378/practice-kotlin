package org.delivery.api.domain.user.business

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.api.domain.token.business.TokenBusiness
import org.delivery.api.domain.token.model.TokenResponse
import org.delivery.api.domain.user.converter.UserConverter
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.user.model.UserLoginRequest
import org.delivery.api.domain.user.model.UserRegisterRequest
import org.delivery.api.domain.user.model.UserResponse
import org.delivery.api.domain.user.service.UserService
import org.delivery.common.annotation.Business
import org.delivery.common.error.UserErrorCode
import org.delivery.db.user.enums.UserStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Business
class UserBusiness(
    private val userService: UserService,
    private val userConverter: UserConverter,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val tokenBusiness: TokenBusiness
) {
    fun register(requestDto: UserRegisterRequest) : TokenResponse {
        // email 중복 체크
        try {
            userService.getUserWithThrow(requestDto.email)
            throw ApiException(UserErrorCode.DUPLICATE_EMAIL)
        } catch (e: ApiException) {
            if (e.errorCodeIfs != UserErrorCode.USER_NOT_FOUND) {
                throw e
            }
        }

        val encodedPassword = passwordEncoder.encode(requestDto.password)
        val encodedUser = requestDto.copy(password = encodedPassword)
        val user = userService.register(userConverter.toEntity(encodedUser, UserStatus.REGISTERED))

        val tokenResponse = tokenBusiness.issueToken(user)
        return tokenResponse
    }

    /**
     * 1. email, password 를 가지고 사용자 체크
     * 2. userEntity 로그인 확인
     * 3. token 발급
     * 4. token response
     */
    fun login(requestDto: UserLoginRequest): TokenResponse {
        val user = userService.getUserWithThrow(requestDto.email)
        if (!passwordEncoder.matches(requestDto.password, user.password)) {
            throw ApiException(ErrorCode.INVALID_PASSWORD)
        }
        val tokenResponse = tokenBusiness.issueToken(user)
        return tokenResponse
    }

    fun me(userDto: User): UserResponse {
        val userId = userDto.id
        val user = userService.getUserWithThrow(userId)
        return userConverter.toResponseDto(user)
    }
}