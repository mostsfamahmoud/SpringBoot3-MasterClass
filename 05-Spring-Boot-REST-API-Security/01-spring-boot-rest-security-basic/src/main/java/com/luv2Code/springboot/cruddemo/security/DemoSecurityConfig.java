// Define the package for the security configuration
package com.luv2Code.springboot.cruddemo.security;

// Import necessary Spring Security classes and annotations

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
     * Configures an in-memory user store with predefined users and their roles.
     * <p>
     * The @Bean annotation registers this method's return value as a Spring-managed bean.
     * The InMemoryUserDetailsManager is used to store user credentials and roles in memory.
     *
     * @return An instance of InMemoryUserDetailsManager containing user details.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        // Create a user with the "EMPLOYEE" role
        UserDetails john = User.builder()
                .username("john")                  // Set the username
                .password("{noop}test123")         // Set the password (no encoding applied)
                .roles("EMPLOYEE")                 // Assign the "EMPLOYEE" role
                .build();

        // Create a user with the "EMPLOYEE" and "MANAGER" roles
        UserDetails mary = User.builder()
                .username("mary")                  // Set the username
                .password("{noop}test123")         // Set the password (no encoding applied)
                .roles("EMPLOYEE", "MANAGER")      // Assign the "EMPLOYEE" and "MANAGER" roles
                .build();

        // Create a user with the "EMPLOYEE," "MANAGER," and "ADMIN" roles
        UserDetails susan = User.builder()
                .username("susan")                 // Set the username
                .password("{noop}test123")         // Set the password (no encoding applied)
                .roles("EMPLOYEE", "MANAGER", "ADMIN") // Assign multiple roles
                .build();

        // Return an InMemoryUserDetailsManager with the predefined users
        return new InMemoryUserDetailsManager(john, mary, susan);
    }


}
