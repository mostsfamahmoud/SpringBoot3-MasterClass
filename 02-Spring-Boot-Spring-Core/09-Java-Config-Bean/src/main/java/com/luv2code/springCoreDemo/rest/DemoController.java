// Define the package for this class
package com.luv2code.springCoreDemo.rest;

// Import necessary Spring Framework classes for dependency injection and REST controllers
import com.luv2code.springCoreDemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a Spring REST controller that handles HTTP requests.
 * It injects a Coach dependency to provide workout suggestions and
 * exposes a REST endpoint to return the daily workout routine.

 * Note: If the IDE shows warnings like "No Usages" for certain classes
 * (e.g., "CricketCoach"), this is due to the dynamic nature of Spring's
 * dependency injection, where bean instantiation and injection happen at runtime.
 * The warning can be ignored as the usage is resolved during runtime by Spring.
 */
@RestController  // Marks this class as a REST controller for Spring to manage
public class DemoController {

    // Step 1: Define a private field for the dependency (Coach interface)
    private Coach myCoach;


    /**
     * Step 2: Define a constructor for dependency injection.

     * The @Autowired annotation tells Spring to inject the appropriate bean
     * for the Coach dependency.
     * If there's only one constructor, the @Autowired annotation is optional as Spring will automatically inject the dependency.
     *
     * @param theCoach - The Coach implementation to be injected by Spring
     */
    @Autowired
    public DemoController(@Qualifier("aquatic") Coach theCoach) {

        // Tracing and Diagnostics
        System.out.println("In Constructor: " + getClass().getSimpleName());

        // Assign the injected Coach instance to the myCoach field
        myCoach = theCoach;
    }


    /**
     * Step 3: Expose a GET endpoint for "/dailyworkout".

     * When a user sends a GET request to "/dailyworkout", this method
     * calls the getDailyWorkout() method of the injected Coach instance
     * and returns the daily workout routine.

     * @return A String containing the daily workout routine
     */
    @GetMapping("/dailyworkout")  // Maps GET requests to "/dailyworkout" to this method
    public String getDailyWorkout() {
        // Return the workout suggestion provided by the Coach implementation
        return myCoach.getDailyWorkout();  // This will call the method of the injected Coach
    }

}

/**
 * Note on IDE Warnings Regarding "No Usages":

 * In some cases, your IDE may display a warning that a class, such as "CricketCoach",
 * has no usages or is not being referenced anywhere. However, despite the warning,
 * we know that the class is indeed being used within our Spring Boot application.

 * This behavior is due to the dynamic nature of Spring's dependency injection.
 * Spring handles the instantiation and management of beans behind the scenes at runtime.
 * As a result, when coding to interfaces (a common practice in Spring applications),
 * we do not explicitly reference the implementation classes, like "CricketCoach", in our code.

 * Instead, we let Spring resolve and inject the appropriate implementation automatically
 * based on the application's configuration and annotations. Due to this dynamic and runtime-based
 * approach, the IDE may not be able to identify the usage of a specific class or method during compile time.

 * In such cases, the IDE's "No Usages" warning can generally be disregarded, as the actual
 * bean injection and usage occur at runtime when the Spring application is executed.

 * This is a normal scenario when working with Spring applications, and the warning does not
 * indicate an issue with your code or project setup.
 */
