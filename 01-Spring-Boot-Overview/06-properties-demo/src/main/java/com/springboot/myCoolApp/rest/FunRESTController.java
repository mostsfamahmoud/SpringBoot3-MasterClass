// Define the package for this class
package com.springboot.myCoolApp.rest;

// Import necessary Spring Framework classes for REST controllers and request mappings
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a REST controller that handles HTTP requests and returns responses.
 * It is part of a Spring Boot application and will handle various requests at different URLs.
 */
@RestController  // Marks this class as a RESTful web service controller
public class FunRESTController {

    /**
     * Injecting properties from the application.properties file.
     * These properties are defined as coach.name and team.name.
     * The @Value annotation is used to inject these values into the variables.
     */
    @Value("${coach.name}")  // Injects the value of 'coach.name' property
    private String coachName;

    @Value("${team.name}")  // Injects the value of 'team.name' property
    private String teamName;

    /**
     * This method handles HTTP GET requests sent to the "/teamInfo" URL.
     * It will return the coach's name and the team's name based on the injected property values.
     *
     * @return A string containing the coach's name and the team's name as the HTTP response body
     */
    @GetMapping("/teamInfo")  // Maps GET requests to the "/teamInfo" URL
    public String getTeamInfo() {
        // Return the team information message, including the coach and team names
        return "Coach Name: " + coachName + ", Team Name: " + teamName;
    }

    /**
     * This method handles HTTP GET requests sent to the root URL ("/").
     * When a user sends a GET request to "/", this method will respond with "Hello World!".
     *
     * @return A string "Hello World!" as the HTTP response body
     */
    @GetMapping("/")  // Maps GET requests to the root URL ("/")
    public String sayHello() {
        // Return the "Hello World!" message to the client
        return "Hello World!";
    }

    /**
     * This method handles HTTP GET requests sent to the "/workout" URL.
     * When a user sends a GET request to "/workout", this method will respond with a workout suggestion.
     *
     * @return A string "Run a Hard 5K!" as the HTTP response body
     */
    @GetMapping("/workout")  // Maps GET requests to the "/workout" URL
    public String getDailyWorkout() {
        // Return a workout message to the client
        return "Run a Hard 5K!";
    }

    /**
     * This method handles HTTP GET requests sent to the "/fortune" URL.
     * When a user sends a GET request to "/fortune", this method will respond with a fortune message.
     *
     * @return A string "Today Is Your Lucky Day!" as the HTTP response body
     */
    @GetMapping("/fortune")  // Maps GET requests to the "/fortune" URL
    public String getDailyFortune() {
        // Return a fortune message to the client
        return "Today Is Your Lucky Day!";
    }
}
