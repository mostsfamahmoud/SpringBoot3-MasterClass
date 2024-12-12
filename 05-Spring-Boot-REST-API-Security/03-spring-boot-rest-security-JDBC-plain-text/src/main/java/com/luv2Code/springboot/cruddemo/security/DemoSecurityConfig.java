// Define the package for the security configuration
package com.luv2Code.springboot.cruddemo.security;

// Import necessary Spring Security classes and annotations

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Security configuration class for setting up in-memory authentication.
 * <p>
 * This class uses the @Configuration annotation to define a Spring configuration
 * for managing application security. It provides a bean for creating an in-memory
 * user store with pre-defined user accounts.
 */
@Configuration
public class DemoSecurityConfig {

    /**
     * Configures a JDBC-based user store for authentication.
     * <p>
     * This method leverages a DataSource (automatically configured by Spring Boot)
     * to authenticate users based on credentials stored in a database. It provides
     * a seamless integration of Spring Security with JDBC.
     *
     * @param dataSource A DataSource object for accessing the database.
     *                   (Automatically injected by Spring Boot.)
     * @return A UserDetailsManager that manages user credentials via JDBC.
     */
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Configure Spring Security to use JDBC Authentication with the provided DataSource
        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     * Configures security filters and authorization rules for the application.
     * <p>
     * This method sets up the authorization rules for accessing the API endpoints
     * and specifies the use of HTTP Basic Authentication for securing the API.
     * CSRF protection is disabled to simplify interactions with a stateless REST API.
     * <p>
     * The @Bean annotation registers the method's return value as a Spring-managed bean,
     * which allows the security configuration to be applied throughout the application.
     *
     * @param httpSecurity A Spring Security object for configuring HTTP security settings.
     * @return A configured SecurityFilterChain that defines the security behavior.
     * @throws Exception If an error occurs during configuration.
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Configure authorization rules for HTTP requests
        httpSecurity.authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                // Allow only users with the EMPLOYEE role to access GET endpoints for employees
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                // Allow only users with the MANAGER role to create new employees
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                // Allow only users with the MANAGER role to update existing employees
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                                // Allow only users with the ADMIN role to delete employees
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // Configure HTTP Basic Authentication for verifying user identity
        // HTTP Basic Authentication requires the client to send a username and password
        // in the HTTP Authorization header using Base64 encoding
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable CSRF (Cross-Site Request Forgery) protection
        // CSRF protection is unnecessary for stateless REST APIs as there is no session state
        // Disabling CSRF simplifies the design for APIs that use HTTP Basic Authentication
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // Finalize the security configuration by building the SecurityFilterChain
        // The returned filter chain is used by Spring Security to process incoming HTTP requests
        return httpSecurity.build();
    }

}

/**
 * Configures an in-memory user store with predefined users and their roles.
 * <p>
 * The @Bean annotation registers this method's return value as a Spring-managed bean.
 * The InMemoryUserDetailsManager is used to store user credentials and roles in memory.
 *
 * @return An instance of InMemoryUserDetailsManager containing user details.
 */
/**
 * @Bean public InMemoryUserDetailsManager userDetailsManager() {
 * // Create a user with the "EMPLOYEE" role
 * UserDetails john = User.builder()
 * .username("john")                  // Set the username
 * .password("{noop}test123")         // Set the password (no encoding applied)
 * .roles("EMPLOYEE")                 // Assign the "EMPLOYEE" role
 * .build();
 * <p>
 * // Create a user with the "EMPLOYEE" and "MANAGER" roles
 * UserDetails mary = User.builder()
 * .username("mary")                  // Set the username
 * .password("{noop}test123")         // Set the password (no encoding applied)
 * .roles("EMPLOYEE", "MANAGER")      // Assign the "EMPLOYEE" and "MANAGER" roles
 * .build();
 * <p>
 * // Create a user with the "EMPLOYEE," "MANAGER," and "ADMIN" roles
 * UserDetails susan = User.builder()
 * .username("susan")                 // Set the username
 * .password("{noop}test123")         // Set the password (no encoding applied)
 * .roles("EMPLOYEE", "MANAGER", "ADMIN") // Assign multiple roles
 * .build();
 * <p>
 * // Return an InMemoryUserDetailsManager with the predefined users
 * return new InMemoryUserDetailsManager(john, mary, susan);
 * }
 */


