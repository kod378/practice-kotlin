package org.delivery.db.user

import org.delivery.db.user.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<org.delivery.db.user.UserEntity, Long> {

    // select * from user where id = ? and status = ? order by id desc limit 1;
    fun findFirstByIdAndStatusOrderByIdDesc(userId: Long?, userStatus: org.delivery.db.user.enums.UserStatus?) : org.delivery.db.user.UserEntity?

    // select * from user where email = ? and status = ? order by id desc limit 1;
    fun findFirstByEmailAndStatusOrderByIdDesc(email: String?, userStatus: org.delivery.db.user.enums.UserStatus?) : org.delivery.db.user.UserEntity?
}