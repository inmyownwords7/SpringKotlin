package org.web.kotlin.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
@Configuration
class SecurityConfig(private val securityProperties: SecurityProperties) {
    @Value("\${spring.security.csrf.enabled}") // Bind to spring.security.csrf.enabled
    private var csrfEnabled: Boolean = true

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/login", "/form", "/css/**", "/js/**", "/public/**").permitAll() // Allow access to /
                    .anyRequest().authenticated() // Protect all other endpoints
            }
        // Conditional CSRF configuration without using `apply`
        if (securityProperties.csrfEnabled) {
            http.csrf { csrf ->
                csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            }
        } else {
            http.csrf { it.disable() }
        }
            .formLogin { login ->
                login
                    .loginPage("/login")
                    .defaultSuccessUrl("/success", true)
                    .permitAll()
            }
            .logout { logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true) // Explicitly clear authentication
                    .deleteCookies("JSESSIONID")
            }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12) // Default strength is 10; increasing it makes hashing more secure
    }
}
