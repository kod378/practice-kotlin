package org.apple.db

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    @field:Column(length = 100)
    var address: String? = null,
    @field:Column(length = 100)
    var detailAddress: String? = null,
    @field:Column
    var postcode: Int? = null,
    @field:Column(length = 20)
    var phoneNumber: String? = null,
    @field:Column(length = 50)
    var addressee: String? = null,
)
