package org.web.kotlin.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UpdateUserDTO
import org.web.kotlin.entity.UserEntity
import org.web.kotlin.exception.UserNotFoundException
import org.web.kotlin.repository.UserRepository
import org.springframework.stereotype.Service
import org.web.kotlin.dto.UserDTO
import org.web.kotlin.utils.toEntity
import org.web.kotlin.utils.toDTO
import java.time.LocalDateTime

@Service
class ServiceLogic(private val userRepository: UserRepository) : UserService {

    override fun createUser(createUserDTO: CreateUserDTO): UserEntity {
        // Validate unique fields
        if (userRepository.existsByUsername(createUserDTO.username)) {
            throw IllegalArgumentException("Username '${createUserDTO.username}' is already taken.")
        }
        if (userRepository.existsByEmail(createUserDTO.email)) {
            throw IllegalArgumentException("Email '${createUserDTO.email}' is already in use.")
        }

        // Convert DTO -> Entity and save
        val user = createUserDTO.toEntity()
        return userRepository.save(user)
    }

    override fun updateUser(userId: Long, updateUserDTO: UpdateUserDTO): UserEntity {
        val user = getUserById(userId)

        // Apply updates (if provided)
        updateUserDTO.username?.let {
            if (userRepository.existsByUsername(it) && user.username != it) {
                throw IllegalArgumentException("Username '${it}' is already in use.")
            }
            user.username = it
        }
        updateUserDTO.email?.let { user.email = it }
        updateUserDTO.firstName?.let { user.firstName = it }
        updateUserDTO.lastName?.let { user.lastName = it }

        // Update timestamp
        user.updatedAt = LocalDateTime.now()
        return userRepository.save(user)
    }

    override fun getUserById(userId: Long): UserEntity {
        return userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with ID $userId not found.") }
    }

    override fun getAllUsers(page: Int, size: Int): Page<UserEntity> {
        val pageable = PageRequest.of(page, size)
        return userRepository.findAll(pageable)
    }
}
