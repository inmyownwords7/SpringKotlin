package org.web.kotlin.utils

import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UserDTO
import org.web.kotlin.entity.UserEntity
import java.time.format.DateTimeFormatter

fun UserEntity.toDTO(): UserDTO {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return UserDTO(
        id = this.id,
        username = this.username,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        createdAt = this.createdAt.format(formatter),
        updatedAt = this.updatedAt.format(formatter)
    )
}

fun CreateUserDTO.toEntity(): UserEntity {
    return UserEntity(
        username = this.username,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName
    )
}


