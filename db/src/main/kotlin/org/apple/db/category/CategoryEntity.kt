package org.apple.db.category

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.apple.db.BaseEntity

@Entity
@Table(name = "category")
class CategoryEntity(
    var name: String,
) : BaseEntity()