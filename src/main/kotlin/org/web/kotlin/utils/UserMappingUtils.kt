package org.web.kotlin.utils

import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UserDTO
import org.web.kotlin.entity.UserEntity
import java.time.format.DateTimeFormatter
private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
fun UserEntity.toDTO(): UserDTO {
// Convert UserEntity -> UserDTO (For API Responses)
    return UserDTO(
        id = this.id,
        username = this.username,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        createdAt = this.createdAt.format(DATE_FORMATTER),
        updatedAt = this.updatedAt.format(DATE_FORMATTER)
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



