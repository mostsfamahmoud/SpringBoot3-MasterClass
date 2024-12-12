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
 * Security configuration class for the application.
 * <p>
 * This class configures Spring Security to secure the application using JDBC-based authentication
 * and defines security filters and authorization rules for various API endpoints.
 */
@Configuration
public class DemoSecurityConfig {

    /**
     * Configures a JDBC-based UserDetailsManager for managing user credentials and roles.
     * <p>
     * This setup integrates Spring Security with a database for authentication and authorization.
     * Custom SQL queries are provided to retrieve user information and roles.
     *
     * @param dataSource The DataSource object used to access the database.
     *                   It is automatically configured by Spring Boot.
     * @return An instance of JdbcUserDetailsManager for database-based authentication.
     */
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Create a JdbcUserDetailsManager with the provided DataSource
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define the query to fetch user details (username, password, and active status)
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id, pw, active " + // Select columns for user details
                        "FROM members " +      // From the "members" table
                        "WHERE user_id = ?"    // Where the user_id matches the given parameter
        );

        // Define the query to fetch user roles
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT * " +                 // Select all columns (adjust for specific schema)
                        "FROM roles " +       // From the "roles" table
                        "WHERE user_id = ?"   // Where the user_id matches the given parameter
        );

        // Return the configured JdbcUserDetailsManager
        return jdbcUserDetailsManager;
    }

    /**
     * Configures the security filter chain and authorization rules for HTTP requests.
     * <p>
     * This method secures API endpoints based on roles and enables HTTP Basic Authentication.
     * CSRF protection is disabled to support a stateless REST API.
     *
     * @param httpSecurity A HttpSecurity object used to configure security settings.
     * @return A SecurityFilterChain that defines the application's security behavior.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Define authorization rules for different HTTP requests
        httpSecurity.authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                // Allow only users with the "EMPLOYEE" role to access GET requests
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                // Allow only users with the "MANAGER" role to create employees
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                // Allow only users with the "MANAGER" role to update employees
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                                // Allow only users with the "ADMIN" role to delete employees
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // Enable HTTP Basic Authentication for user identity verification
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable CSRF protection (appropriate for stateless REST APIs)
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // Build and return the SecurityFilterChain
        return httpSecurity.build();
    }

}

/**
 * A commented-out example of in-memory authentication.
 * <p>
 * This code snippet demonstrates how to define a UserDetailsManager with predefined users and roles,
 * useful for quick testing or applications that don't require database-backed authentication.
 *
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
