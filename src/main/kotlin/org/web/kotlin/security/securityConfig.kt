package org.web.kotlin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

/**
 * Security configuration class to define HTTP security settings, including CSP and header configurations.
 */
@Configuration
class SecurityConfig {

    /**
     * Configures the HTTP security settings.
     *
     * @param http the HttpSecurity object used to configure security
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/login", "/css/**", "/js/**", "/public/**").permitAll()
                    .anyRequest().authenticated()
            }
            .headers { headers ->
                headers.contentSecurityPolicy { csp ->
                    csp.policyDirectives("default-src 'self'; script-src 'self'; style-src 'self'")
                }
                headers.frameOptions { it.deny() }
            }
            .csrf { it.disable() }

        return http.build()
    }
}
