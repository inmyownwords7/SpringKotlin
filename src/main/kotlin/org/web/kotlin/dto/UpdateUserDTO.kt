package org.web.kotlin.dto

data class UpdateUserDTO(
    var username: String? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null
)
