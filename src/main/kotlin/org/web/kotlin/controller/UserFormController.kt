package org.web.kotlin.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.web.kotlin.service.UserService

@Controller
@RequestMapping("/users")
class UserFormController(private val userService: UserService) {

    @GetMapping("/form")
    fun showCreateUserForm(model: Model): String {
        model.addAttribute("message", null) // Optional message attribute
        return "create-user" // Maps to create-user.mustache or create-user.html
    }

    @PostMapping
    fun createUser(
        @RequestParam("username") username: String,
        @RequestParam("email") email: String,
        @RequestParam("firstName") firstName: String,
        @RequestParam("lastName") lastName: String,
        model: Model
    ): String {
        // Call service method to create the user
        userService.createUser(username, email, firstName, lastName)
        model.addAttribute("message", "User created successfully!")
        return "create-user" // Renders the form template with a success message
    }
}
