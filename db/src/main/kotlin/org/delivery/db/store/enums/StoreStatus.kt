package org.delivery.db.store.enums

enum class StoreStatus(
    val description: String,
) {
    OPEN("영업 중"),
    CLOSE("영업 종료"),
    REGISTERED("등록"),
    UNREGISTERED("해지"),
}