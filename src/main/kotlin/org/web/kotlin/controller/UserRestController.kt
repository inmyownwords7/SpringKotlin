package org.web.kotlin.controller

import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UpdateUserDTO
import org.web.kotlin.dto.UserDTO
import org.web.kotlin.service.UserService
import org.web.kotlin.utils.toDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
//import org.web.kotlin.entity.UserEntity
import org.web.kotlin.exception.UserNotFoundException
import org.springframework.validation.annotation.Validated
@Validated
@RestController
@RequestMapping("/api/users")
class UserRestController(private val userService: UserService) {
    /**
     * Create a new user.
     *
     * @param createUserDTO User details for creation.
     * @return The created user.
     * @throws MethodArgumentNotValidException if validation fails.
     */
    @PostMapping
    fun createUser(@Valid @RequestBody createUserDTO: CreateUserDTO): ResponseEntity<UserDTO> {
        val user = userService.createUser(createUserDTO).toDTO()
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    @PatchMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody updateUserDTO: UpdateUserDTO
    ): ResponseEntity<Void> {
        userService.updateUser(id, updateUserDTO)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        return try {
            val user = userService.getUserById(id).toDTO()
            ResponseEntity.ok(user)
        } catch (e: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
    @GetMapping
    fun getAllUsers(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int
    ): ResponseEntity<Page<UserDTO>> {
        val users = userService.getAllUsers(page, size).map { it.toDTO() }
        return ResponseEntity.ok(users)
    }
}
