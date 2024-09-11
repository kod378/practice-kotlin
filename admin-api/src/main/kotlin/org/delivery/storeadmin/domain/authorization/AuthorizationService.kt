package org.delivery.storeadmin.domain.authorization

import org.delivery.db.store.StoreRepository
import org.delivery.db.store.enums.StoreStatus
import org.delivery.storeadmin.domain.authorization.model.UserSession
import org.delivery.storeadmin.domain.store.model.StoreSimpleResponse
import org.delivery.storeadmin.domain.store.service.StoreService
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val storeUserService: StoreUserService,
    private val storeService: StoreService
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        username ?: throw UsernameNotFoundException("username이 없습니다.")

        val storeUserEntity = storeUserService.getRegisterUser(username)

        val storeEntity = storeUserEntity.storeId?.let {
            storeService.getStoreWithThrow(it)
        }

        val user = UserSession(
            id = storeUserEntity.id!!,
            email = storeUserEntity.email,
            _password = storeUserEntity.password,
            status = storeUserEntity.status,
            role = storeUserEntity.role,
            registeredAt = storeUserEntity.registeredAt!!,
            unregisteredAt = storeUserEntity.unregisteredAt,
            lastLoginAt = storeUserEntity.lastLoginAt,
            storeSimpleResponse = storeEntity?.let { StoreSimpleResponse(
                id = it.id!!,
                name = it.name,
            ) }
        )

        println("로그인 성공: $user")
        return user
    }
}