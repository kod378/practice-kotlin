package org.apple.db.user

import org.apple.db.user.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    // select * from user where id = ? and status = ? order by id desc limit 1;
    fun findFirstByIdAndStatusOrderByIdDesc(userId: Long?, userStatus: UserStatus) : UserEntity?

    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1;
    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email: String, password: String, userStatus: UserStatus) : UserEntity?
}