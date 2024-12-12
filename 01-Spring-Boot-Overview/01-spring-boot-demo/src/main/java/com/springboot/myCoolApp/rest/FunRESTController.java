// Define the package for this class
package com.springboot.myCoolApp.rest;

// Import necessary Spring Framework classes for REST controllers and request mappings
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a REST controller that handles HTTP requests and returns responses.
 * It is part of a Spring Boot application and will handle requests to the root URL ("/").
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
}
