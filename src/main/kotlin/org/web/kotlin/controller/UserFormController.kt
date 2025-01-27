package org.web.kotlin.controller

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.web.kotlin.dto.CreateUserDTO
import org.web.kotlin.service.UserService

@Controller
@RequestMapping("/")
class UserFormController(private val userService: UserService) {

    // Serve the home page (can redirect to login or other logic)
    @GetMapping
    fun showHomePage(): String {
        return "redirect:/login" // Redirect to the login page by default
    }

    // Serve the login page
    @GetMapping("/login")
    fun showLoginPage(model: Model): String {
        return "login" // Maps to login.html
    }

    // Serve the user registration form
    @GetMapping("/form")
    fun showCreateUserForm(model: Model): String {
        return "create-user" // Maps to create-user.html or mustache template
    }

    // Handle user creation
    @PostMapping("/form")
    fun createUser(
        @Valid createUserDTO: CreateUserDTO,
        bindingResult: BindingResult,
        model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Please fix the errors in the form.")
            model.addAttribute("errors", bindingResult.allErrors)
            return "create-user" // Renders the form with errors
        }

        return try {
            // Call service method to create the user
            userService.createUser(createUserDTO)
            "redirect:/login?registration=success" // Redirect to login with success message
        } catch (e: Exception) {
            model.addAttribute("message", "Error creating user: ${e.message}")
            model.addAttribute("createUserDTO", createUserDTO) // Retain entered values
            return "create-user" // Renders the form with error message
        }
    }
}
