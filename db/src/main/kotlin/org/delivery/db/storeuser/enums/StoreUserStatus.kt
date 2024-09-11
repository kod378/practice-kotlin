package org.delivery.db.storeuser.enums

enum class StoreUserStatus(
    private val description: String
) {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
}