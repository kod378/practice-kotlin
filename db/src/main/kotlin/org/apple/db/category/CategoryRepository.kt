package org.apple.db.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {

    fun findCategoryById(id:Long?):CategoryEntity?
}