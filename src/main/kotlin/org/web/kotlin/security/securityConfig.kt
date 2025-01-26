package org.web.kotlin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Disable CSRF
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/public/**").permitAll() // Public endpoints
                    .anyRequest().authenticated() // Protect all other endpoints
            }
            .formLogin { it.defaultSuccessUrl("/home", true) } // Enable form login
            .httpBasic { } // Enable HTTP Basic authentication
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder() // Use BCrypt for password hashing
    }
}
