package org.web.kotlin.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class UserDetailsConfig {
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user: UserDetails = User.builder()
            .username("admin")
            .password("{bcrypt}${BCryptPasswordEncoder().encode("password")}")
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
