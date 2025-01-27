package org.web.kotlin.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UpdateUserDTO(
    @field:Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    var username: String? = null,

    @field:Email(message = "Invalid email format")
    var email: String? = null,

    @field:Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    var firstName: String? = null,

    @field:Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    var lastName: String? = null
)
