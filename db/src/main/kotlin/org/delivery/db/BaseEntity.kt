package org.delivery.db

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:CreatedDate
    @field:Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @field:LastModifiedDate
    @field:Column
    var updatedAt: LocalDateTime? = null,
)