package org.delivery.api.domain.user.service

import org.delivery.common.error.UserErrorCode
import org.delivery.common.exception.ApiException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: org.delivery.db.user.UserRepository
) {

//    fun getUserWithThrow(userId: Long) : UserEntity {
//        val user = userRepository.findUserById(userId);
//        return user?: throw RuntimeException("Find userId : $userId, But user not Found")
//    }

    @Transactional
    fun register(userEntity: org.delivery.db.user.UserEntity) : org.delivery.db.user.UserEntity {
        return userRepository.save(userEntity)
    }

    fun login(email: String, password: String) {
        val user = getUserWithThrow(email)
        //TODO
    }

    fun getUserWithThrow(email: String) : org.delivery.db.user.UserEntity {
        return userRepository.findFirstByEmailAndStatusOrderByIdDesc(email, org.delivery.db.user.enums.UserStatus.REGISTERED)
            ?: throw ApiException(UserErrorCode.USER_NOT_FOUND)
    }

    fun getUserWithThrow(userId: Long?): org.delivery.db.user.UserEntity {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, org.delivery.db.user.enums.UserStatus.REGISTERED)
            ?: throw ApiException(UserErrorCode.USER_NOT_FOUND)
    }
}