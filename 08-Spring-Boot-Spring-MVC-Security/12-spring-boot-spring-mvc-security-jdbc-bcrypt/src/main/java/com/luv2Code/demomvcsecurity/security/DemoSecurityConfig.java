// Define the package where the security configuration resides
package com.luv2Code.demomvcsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Security configuration class for managing user authentication and authorization.
 * This class defines users, roles, and security rules using Spring Security.
 */
@Configuration // Marks this class as a Spring configuration class
public class DemoSecurityConfig {

    /**
     * Configures JDBC authentication using the provided data source.
     * Spring Security will use this to authenticate users from the database.
     *
     * @param dataSource The database source for authentication (Auto-configured by Spring Boot)
     * @return A UserDetailsManager that uses JDBC for authentication.
     */
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Configure Spring Security to use JDBC authentication with the provided data source
        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     * Defines the security filter chain for handling authentication and authorization.
     *
     * @param httpSecurity The security configuration for HTTP requests.
     * @return A configured SecurityFilterChain object that defines security rules.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // Define authorization rules for different endpoints
                .authorizeHttpRequests(
                        matcherRegistry ->
                                matcherRegistry
                                        .requestMatchers("/").hasRole("EMPLOYEE") // Home page is accessible to users with the EMPLOYEE role
                                        .requestMatchers("/leaders/**").hasRole("MANAGER") // "leaders" pages accessible only to MANAGERS
                                        .requestMatchers("/systems/**").hasRole("ADMIN") // "systems" pages accessible only to ADMINS
                                        .anyRequest() // Any other request must be authenticated
                                        .authenticated()
                )
                // Configure the login form settings
                .formLogin(
                        formLoginConfigurer ->
                                formLoginConfigurer
                                        .loginPage("/showMyCustomLoginForm") // Custom login page URL
                                        .loginProcessingUrl("/authenticateTheUser") // URL where login credentials are submitted for authentication
                                        .permitAll() // Allow everyone to access the login page
                )
                // Enable logout functionality
                .logout(LogoutConfigurer::permitAll) // Enable logout at the default "/logout" URL
                // Configure exception handling for unauthorized access
                .exceptionHandling(handlingConfigurer ->
                        handlingConfigurer.accessDeniedPage("/access-denied") // Redirect users to the "access-denied" page if they lack permissions
                );

        // Build and return the security configuration
        return httpSecurity.build();
    }
}
