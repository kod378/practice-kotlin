package org.delivery.storeadmin.domain.storeuser.business

import org.delivery.common.annotation.Business
import org.delivery.common.error.StoreUserErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreRepository
import org.delivery.storeadmin.domain.storeuser.converter.StoreUserConverter
import org.delivery.storeadmin.domain.authorization.model.StoreUserRegisterRequest
import org.delivery.storeadmin.domain.storeuser.model.StoreUserResponse
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService

@Business
class StoreUserBusiness(
    private val storeUserService: StoreUserService,
    private val storeUserConverter: StoreUserConverter,
) {

    fun register(request: StoreUserRegisterRequest): StoreUserResponse {
        val storeUserEntity = storeUserConverter.toEntity(request)

        //check duplication
        try {
            storeUserService.getRegisterUser(storeUserEntity.email)
            throw ApiException(StoreUserErrorCode.DUPLICATE_USER)
        } catch (e: ApiException) {
            if (e.errorCodeIfs != StoreUserErrorCode.NOT_FOUND_USER) {
                throw e
            }
        }

        val registeredUser = storeUserService.register(storeUserEntity)

        val response = storeUserConverter.toResponse(registeredUser, null)

        return response
    }
}