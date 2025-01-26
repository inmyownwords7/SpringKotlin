package org.web.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.web.kotlin.entity.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
//    fun findByUsername(username: String): UserEntity?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}
