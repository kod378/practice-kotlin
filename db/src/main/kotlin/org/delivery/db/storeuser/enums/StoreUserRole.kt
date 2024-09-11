package org.delivery.db.storeuser.enums

enum class StoreUserRole(
    private val description: String
) {
    MASTER("마스터"),
    ADMIN("관리자"),
    USER("일반유저"),
}