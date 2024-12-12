// Define the package for the security configuration
package com.luv2Code.springboot.cruddemo.security;

// Import necessary Spring Security classes and annotations

import com.luv2Code.springboot.cruddemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Security configuration class for setting up authentication and authorization.
 * <p>
 * This class uses the @Configuration annotation to define a Spring Security setup for the application.
 * It manages password encoding, authentication providers, and security filters for REST API endpoints.
 */
@Configuration
public class DemoSecurityConfig {

    // Define beans for password encoding and authentication.

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
     * Configures security filters and authorization rules for the application.
     * <p>
     * This method defines the security behavior for various API endpoints, enabling HTTP Basic Authentication
     * and applying role-based access control. CSRF protection is disabled to simplify API interactions.
     *
     * @param httpSecurity A Spring Security object for configuring HTTP security settings.
     * @return A configured SecurityFilterChain that defines the security behavior.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Configure authorization rules for HTTP requests
        httpSecurity.authorizeHttpRequests(auth -> auth
                // Allow only users with the EMPLOYEE role to access GET endpoints for employees
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                // Allow only users with the MANAGER role to create or update employees
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                // Allow only users with the ADMIN role to delete employees
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // Configure HTTP Basic Authentication for verifying user identity
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable CSRF protection for stateless REST APIs
        httpSecurity.csrf(csrf -> csrf.disable());

        // Build and return the security filter chain
        return httpSecurity.build();
    }

    /**
     * Configures a JDBC-based UserDetailsManager for managing user credentials and roles.
     * <p>
     * This setup integrates Spring Security with a database for authentication and authorization.
     * Custom SQL queries are provided to retrieve user information and roles.
     *
     * @param dataSource The DataSource object used to access the database.
     * @return An instance of JdbcUserDetailsManager for database-based authentication.
     */
    /*@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define the query to fetch user details (username, password, and active status)
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT user_id, pw, active " +
            "FROM members " +
            "WHERE user_id = ?"
        );

        // Define the query to fetch user roles
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT * " +
            "FROM roles " +
            "WHERE user_id = ?"
        );

        return jdbcUserDetailsManager;
    }*/

    /**
     * Configures an in-memory user store with predefined users and their roles.
     * <p>
     * This is an alternative configuration for testing or development purposes.
     * It stores user details directly in memory, without a database.
     *
     * @return An instance of InMemoryUserDetailsManager containing user details.
     */
    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
            .username("john")
            .password("{noop}test123") // No password encoding
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}test123") // No password encoding
            .roles("EMPLOYEE", "MANAGER")
            .build();

        UserDetails susan = User.builder()
            .username("susan")
            .password("{noop}test123") // No password encoding
            .roles("EMPLOYEE", "MANAGER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }*/
}
