// Define the package where the security configuration resides
package com.luv2Code.demomvcsecurity.security;

import com.luv2Code.demomvcsecurity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Security configuration class for managing user authentication and authorization.
 * This class defines users, roles, and security rules using Spring Security.
 */
@Configuration // Marks this class as a Spring configuration class
public class DemoSecurityConfig {


    /**
     * Provides a BCrypt password encoder bean.
     * <p>
     * This bean is used to hash and verify user passwords securely.
     * BCrypt adds a unique salt to each password and is resistant to brute-force attacks.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures a DAO-based authentication provider.
     * <p>
     * This method sets up a `DaoAuthenticationProvider` for managing authentication using
     * a database or other persistent storage. The `DaoAuthenticationProvider` integrates
     * with a custom `UserService`, which implements `UserDetailsService` to fetch user credentials
     * and roles from a data source.
     * <p>
     * Password validation is performed using the `BCryptPasswordEncoder` for secure password storage
     * and verification.
     *
     * @param userService The custom `UserService` implementation that provides user data and roles.
     *                    This service is responsible for fetching user details from the database or another source.
     * @return A fully configured `DaoAuthenticationProvider` instance ready for use in authentication processes.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        // Create an instance of DaoAuthenticationProvider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Set the custom UserDetailsService to load user data from the UserService
        authProvider.setUserDetailsService(userService);

        // Set the password encoder to BCryptPasswordEncoder for hashing and validating passwords
        authProvider.setPasswordEncoder(passwordEncoder());

        // Return the configured authentication provider
        return authProvider;
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

/**
 * Configures JDBC authentication using the provided data source.
 * Spring Security will use this to authenticate users from the database.
 *
 * @param dataSource The database source for authentication (Auto-configured by Spring Boot)
 * @return A UserDetailsManager that uses JDBC for authentication.
 */
/*
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource) {
    // Configure Spring Security to use JDBC authentication with the provided data source
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    // Define a query to retrieve a user by a username
    jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT user_id, pw, active " +
                    "FROM members " +
                    "WHERE user_id=?");

    // Define a query to retrieve authorities by a username
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT user_id, role" +
                    "FROM roles" +
                    "WHERE user_id=?");

    return jdbcUserDetailsManager;
}
 */
