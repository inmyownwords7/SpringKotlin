package org.web.kotlin.controller

import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UpdateUserDTO
import org.web.kotlin.dto.UserDTO
import org.web.kotlin.service.UserService
import org.web.kotlin.utils.toDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserRestController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody createUserDTO: CreateUserDTO): ResponseEntity<UserDTO> {
        val user = userService.createUser(createUserDTO).toDTO()
        return ResponseEntity.ok(user)
    }

    @PatchMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody updateUserDTO: UpdateUserDTO
    ): ResponseEntity<UserDTO> {
        val updatedUser = userService.updateUser(id, updateUserDTO).toDTO()
        return ResponseEntity.ok(updatedUser)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val user = userService.getUserById(id).toDTO()
        return ResponseEntity.ok(user)
    }
}
