package org.apple.api.domain.user.service

import org.apple.db.user.UserEntity
import org.apple.db.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

//    fun getUserWithThrow(userId: Long) : UserEntity {
//        val user = userRepository.findUserById(userId);
//        return user?: throw RuntimeException("Find userId : $userId, But user not Found")
//    }

    @Transactional
    fun register(userEntity: UserEntity) : UserEntity {
        return userRepository.save(userEntity)
    }
}