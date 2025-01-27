package org.web.kotlin.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.entity.UserEntity
import org.web.kotlin.exception.UserNotFoundException
import org.web.kotlin.repository.UserRepository
import java.util.*
import org.junit.jupiter.api.assertThrows

@ExtendWith(MockitoExtension::class)
class ServiceLogicTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var serviceLogic: ServiceLogic<Any?>

    @Test
    fun `should create a new user`() {
        // Arrange
        val createUserDTO = CreateUserDTO("testuser", "test@example.com", "Test", "User")
        val savedUser = UserEntity(
            id = 1,
            username = "testuser",
            email = "test@example.com",
            firstName = "Test",
            lastName = "User"
        )

        `when`(userRepository.existsByUsername("testuser")).thenReturn(false)
        `when`(userRepository.existsByEmail("test@example.com")).thenReturn(false)
        `when`(userRepository.save(any())).thenReturn(savedUser)

        // Act
        val result = serviceLogic.createUser(createUserDTO)

        // Assert
        assertEquals(1, result.id)
        assertEquals("testuser", result.username)
    }

    @Test
    fun `should throw UserNotFoundException when user does not exist`() {
        // Arrange
        `when`(userRepository.findById(99)).thenReturn(Optional.empty())

        // Act & Assert
        val exception = assertThrows<UserNotFoundException> {
            serviceLogic.getUserById(99)
        }
        assertEquals("User with ID 99 not found.", exception.message)


        assertEquals("User with ID 99 not found.", exception.message)
    }
}
