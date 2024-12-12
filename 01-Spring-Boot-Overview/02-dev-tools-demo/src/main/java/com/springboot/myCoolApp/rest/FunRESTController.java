// Define the package for this class
package com.springboot.myCoolApp.rest;

// Import necessary Spring Framework classes for REST controllers and request mappings
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a REST controller that handles HTTP requests and returns responses.
 * It is part of a Spring Boot application and will handle various requests at different URLs.
 */
@RestController  // Marks this class as a RESTful web service controller
public class FunRESTController {

    /**
     * This method handles HTTP GET requests sent to the root URL ("/").
     * When a user sends a GET request to "/", this method will respond with "Hello World!".
     *
     * @return A string "Hello World!" as the HTTP response body
     */
    @GetMapping("/")  // Maps GET requests to the "/" URL to this method
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
