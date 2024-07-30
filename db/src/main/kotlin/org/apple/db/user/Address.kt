package org.apple.db.user

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    @Column
    val city: String,
    @Column
    val street: String,
    @Column
    val zipCode: String,
)
