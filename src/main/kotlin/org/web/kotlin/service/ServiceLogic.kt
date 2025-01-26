package org.web.kotlin.service

import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.dto.UpdateUserDTO
import org.web.kotlin.entity.UserEntity
import org.web.kotlin.exception.UserNotFoundException
import org.web.kotlin.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ServiceLogic(private val userRepository: UserRepository) : UserService {

    // Overloaded method for individual parameters
    override fun createUser(username: String, email: String, firstName: String, lastName: String): UserEntity {
        // Delegate logic to the existing createUser(CreateUserDTO) method
        val createUserDTO = CreateUserDTO(
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName
        )
        return createUser(createUserDTO)
    }

    // Existing method that uses CreateUserDTO
    override fun createUser(createUserDTO: CreateUserDTO): UserEntity {
        if (userRepository.existsByUsername(createUserDTO.username)) {
            throw IllegalArgumentException("Username '${createUserDTO.username}' is already taken.")
        }
        if (userRepository.existsByEmail(createUserDTO.email)) {
            throw IllegalArgumentException("Email '${createUserDTO.email}' is already in use.")
        }
        val user = UserEntity(
            username = createUserDTO.username,
            email = createUserDTO.email,
            firstName = createUserDTO.firstName,
            lastName = createUserDTO.lastName
        )
        return userRepository.save(user)
    }

    override fun updateUser(userId: Long, updateUserDTO: UpdateUserDTO): UserEntity {
        val user = getUserById(userId)
        updateUserDTO.username?.let { user.username = it }
        updateUserDTO.email?.let { user.email = it }
        updateUserDTO.firstName?.let { user.firstName = it }
        updateUserDTO.lastName?.let { user.lastName = it }
        user.updatedAt = LocalDateTime.now()
        return userRepository.save(user)
    }

    override fun getUserById(userId: Long): UserEntity {
        return userRepository.findById(userId)
            .orElseThrow { -> UserNotFoundException("User with ID $userId not found.") }
    }
}
