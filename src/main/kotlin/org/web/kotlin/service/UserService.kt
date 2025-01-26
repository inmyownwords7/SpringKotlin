package org.web.kotlin.service

import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UpdateUserDTO
import org.web.kotlin.entity.UserEntity

interface UserService {
    fun createUser(createUserDTO: CreateUserDTO): UserEntity
    fun updateUser(userId: Long, updateUserDTO: UpdateUserDTO): UserEntity
    fun getUserById(userId: Long): UserEntity
    fun createUser(username: String, email: String, firstName: String, lastName: String): UserEntity
}
