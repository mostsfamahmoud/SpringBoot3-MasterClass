// Define the package where the security configuration resides
package com.luv2Code.demomvcsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for managing user authentication and authorization.
 * This class defines users, roles, and security rules using Spring Security.
 */
@Configuration // Marks this class as a configuration class for Spring
public class DemoSecurityConfig {

    /**
     * Defines an in-memory user details manager.
     * This method creates users with predefined usernames, passwords, and roles.
     *
     * @return InMemoryUserDetailsManager containing user details.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        // Define a user "john" with the role of "EMPLOYEE"
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123") // {noop} means no password encoding is used
                .roles("EMPLOYEE") // Assign the "EMPLOYEE" role to the user
                .build();

        // Define a user "mary" with roles "EMPLOYEE" and "MANAGER"
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER") // Assign multiple roles
                .build();

        // Define a user "susan" with roles "EMPLOYEE", "MANAGER", and "ADMIN"
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        // Return the user details manager containing all users
        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    /**
     * Defines the security filter chain for handling authentication and authorization.
     *
     * @param httpSecurity The security configuration for HTTP requests.
     * @return Configured SecurityFilterChain object.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(
                        matcherRegistry ->
                                matcherRegistry
                                        .requestMatchers("/").hasRole("EMPLOYEE") // Home page accessible to EMPLOYEES
                                        .requestMatchers("/leaders/**").hasRole("MANAGER") // "leaders" pages accessible to MANAGERS
                                        .requestMatchers("/systems/**").hasRole("ADMIN") // "systems" pages accessible to ADMINS
                                        .anyRequest() // Any other request must be authenticated
                                        .authenticated()
                )
                .formLogin(
                        formLoginConfigurer ->
                                formLoginConfigurer
                                        .loginPage("/showMyCustomLoginForm") // Custom login form URL
                                        .loginProcessingUrl("/authenticateTheUser") // URL where login credentials are submitted
                                        .permitAll() // Allow all users to access the login page
                )
                .logout(LogoutConfigurer::permitAll) // Enable logout functionality at default "/logout" URL
                .exceptionHandling(handlingConfigurer ->
                        handlingConfigurer.accessDeniedPage("/access-denied") // Redirect to "access-denied" page if access is restricted
                );

        return httpSecurity.build(); // Build and return the security configuration
    }
}
