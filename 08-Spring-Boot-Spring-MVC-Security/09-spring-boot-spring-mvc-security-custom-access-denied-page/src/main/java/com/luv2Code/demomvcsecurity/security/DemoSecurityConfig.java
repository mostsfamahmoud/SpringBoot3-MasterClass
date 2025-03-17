package com.luv2Code.demomvcsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }


    // Modifying the Spring Security Config to reference My Custom Login form

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(
                        matcherRegistry ->
                                matcherRegistry
                                        .requestMatchers("/").hasRole("EMPLOYEE")
                                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                                        .requestMatchers("/systems/**").hasRole("ADMIN")
                                        .anyRequest() // Any request to the app must be authenticated (Logged-In)
                                        .authenticated()
                )
                .formLogin(
                        formLoginConfigurer ->
                                formLoginConfigurer
                                        .loginPage("/showMyCustomLoginForm") // Show the custom login form at the request mapping
                                        .loginProcessingUrl("/authenticateTheUser") // Login form should POST data to this URL for processing
                                        .permitAll() // Allow everyone to see the login page (No Need to be logged in)
                )
                .logout(LogoutConfigurer::permitAll) // Adding logout support for default URL (/logout)
                .exceptionHandling(handlingConfigurer ->
                        handlingConfigurer.accessDeniedPage("/access-denied")
                );
        ;


        return httpSecurity.build();
    }

}
