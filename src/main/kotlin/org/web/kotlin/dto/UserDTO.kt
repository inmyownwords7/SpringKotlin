package org.web.kotlin.dto

import com.fasterxml.jackson.annotation.JsonFormat

data class UserDTO(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: String
)
