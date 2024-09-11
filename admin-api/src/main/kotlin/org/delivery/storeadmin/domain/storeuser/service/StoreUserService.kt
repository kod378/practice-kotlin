package org.delivery.storeadmin.domain.storeuser.service

import org.delivery.common.error.StoreUserErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.storeuser.StoreUserEntity
import org.delivery.db.storeuser.StoreUserRepository
import org.delivery.db.storeuser.enums.StoreUserStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StoreUserService(
    private val storeUserRepository: StoreUserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(storeUserEntity: StoreUserEntity): StoreUserEntity {
        storeUserEntity.status = StoreUserStatus.REGISTERED
        storeUserEntity.registeredAt = LocalDateTime.now()
        storeUserEntity.password = passwordEncoder.encode(storeUserEntity.password)
        return storeUserRepository.save(storeUserEntity)
    }

    fun getRegisterUser(email: String): StoreUserEntity {
        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED)
            ?: throw ApiException(StoreUserErrorCode.NOT_FOUND_USER)
    }

    fun getRegisterUser(id: Long): StoreUserEntity {
        return storeUserRepository.findByIdAndStatus(id, StoreUserStatus.REGISTERED)
            ?: throw ApiException(StoreUserErrorCode.NOT_FOUND_USER)
    }
}