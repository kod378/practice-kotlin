package org.delivery.storeadmin.domain.authorization.model

import org.delivery.db.storeuser.enums.StoreUserRole
import org.delivery.db.storeuser.enums.StoreUserStatus
import org.delivery.storeadmin.domain.store.model.StoreResponse
import org.delivery.storeadmin.domain.storeuser.ifs.HasStore
import org.delivery.storeadmin.domain.store.model.StoreSimpleResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class UserSession(
    // user
    val id: Long,
    val email: String,
    private val _password: String,
    val status: StoreUserStatus,
    val role: StoreUserRole,
    val registeredAt: LocalDateTime,
    val unregisteredAt: LocalDateTime? = null,
    val lastLoginAt: LocalDateTime? = null,

    // store
//    override val storeSimpleResponse: StoreSimpleResponse? = null
    override val storeResponse: StoreResponse? = null
): UserDetails, HasStore {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(this.role.name))
    }

    override fun getPassword(): String {
        return this._password
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return this.status == org.delivery.db.storeuser.enums.StoreUserStatus.REGISTERED
    }

    override fun isAccountNonLocked(): Boolean {
        return this.status == org.delivery.db.storeuser.enums.StoreUserStatus.REGISTERED
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.status == org.delivery.db.storeuser.enums.StoreUserStatus.REGISTERED
    }
}