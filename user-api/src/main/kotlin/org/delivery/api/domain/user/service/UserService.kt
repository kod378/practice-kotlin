package org.delivery.api.domain.user.service

import org.delivery.common.error.UserErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.user.UserEntity
import org.delivery.db.user.enums.UserStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: org.delivery.db.user.UserRepository
) {

    fun register(userEntity: UserEntity) : UserEntity {
        return userRepository.save(userEntity)
    }

    fun login(email: String, password: String) {
        val user = getUserWithThrow(email)
        //TODO
    }

    fun getUserWithThrow(email: String) : UserEntity {
        return userRepository.findFirstByEmailAndStatusOrderByIdDesc(email, UserStatus.REGISTERED)
            ?: throw ApiException(UserErrorCode.USER_NOT_FOUND)
    }

    fun getUserWithThrow(userId: Long?): UserEntity {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
            ?: throw ApiException(UserErrorCode.USER_NOT_FOUND, "Find userId : $userId, But user not Found")
    }
}