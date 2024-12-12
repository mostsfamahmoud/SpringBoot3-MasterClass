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
 *
 * The class also demonstrates the usage of Spring's dependency injection
 * for managing beans and highlights the difference between singleton
 * and prototype bean scopes.
 *
 * Note: If the IDE shows warnings like "No Usages" for certain classes
 * (e.g., "CricketCoach"), this is due to the dynamic nature of Spring's
 * dependency injection, where bean instantiation and injection happen at runtime.
 * The warning can be ignored as the usage is resolved during runtime by Spring.
 */
@RestController  // Marks this class as a REST controller for Spring to manage
public class DemoController {

    // Step 1: Define private fields for the dependencies (Coach interface)
    private Coach myCoach;
    private Coach anotherCoach;

    /**
     * Step 2: Define a constructor for dependency injection.
     *
     * The @Autowired annotation tells Spring to inject the appropriate bean
     * for the Coach dependency. The @Qualifier annotation specifies which
     * implementation of the Coach interface should be injected. In this case,
     * we are specifying "cricketCoach" for both fields.
     *
     * @param theCoach - The Coach implementation to be injected by Spring
     * @param theAnotherCoach - Another Coach instance to demonstrate bean comparison
     */
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach,
                          @Qualifier("cricketCoach") Coach theAnotherCoach) {

        // Tracing and Diagnostics
        System.out.println("In Constructor: " + getClass().getSimpleName());

        // Assign the injected Coach instances to the fields
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    /**
     * Step 3: Expose a GET endpoint for "/dailyworkout".
     *
     * When a user sends a GET request to "/dailyworkout", this method
     * calls the getDailyWorkout() method of the injected Coach instance
     * and returns the daily workout routine.
     *
     * @return A String containing the daily workout routine
     */
    @GetMapping("/dailyworkout")  // Maps GET requests to "/dailyworkout" to this method
    public String getDailyWorkout() {
        // Return the workout suggestion provided by the injected Coach implementation
        return myCoach.getDailyWorkout();
    }

    /**
     * Step 4: Expose a GET endpoint for "/check" to compare beans.
     *
     * This method compares two injected Coach beans (myCoach and anotherCoach)
     * to determine if they are the same instance or different instances.
     *
     * This helps demonstrate the difference between singleton and prototype bean scopes.
     *
     * @return A String indicating whether the two beans are the same instance or different instances
     */
    @GetMapping("/check")  // Maps GET requests to "/check" to this method
    public String check() {
        // Compare the two beans and return the result along with the scope type
        return "Comparing Beans: myCoach == anotherCoach ? "
                + (myCoach == anotherCoach)  // Compare the two beans
                + " -> "
                + ((myCoach == anotherCoach) ? "Singleton" : "Prototype")  // Determine scope type
                + " Bean Scope";
    }
}

/**
 * Note on IDE Warnings Regarding "No Usages":
 *
 * In some cases, your IDE may display a warning that a class, such as "CricketCoach",
 * has no usages or is not being referenced anywhere. However, despite the warning,
 * we know that the class is indeed being used within our Spring Boot application.
 *
 * This behavior is due to the dynamic nature of Spring's dependency injection.
 * Spring handles the instantiation and management of beans behind the scenes at runtime.
 * As a result, when coding to interfaces (a common practice in Spring applications),
 * we do not explicitly reference the implementation classes, like "CricketCoach", in our code.
 *
 * Instead, we let Spring resolve and inject the appropriate implementation automatically
 * based on the application's configuration and annotations. Due to this dynamic and runtime-based
 * approach, the IDE may not be able to identify the usage of a specific class or method during compile time.
 *
 * In such cases, the IDE's "No Usages" warning can generally be disregarded, as the actual
 * bean injection and usage occur at runtime when the Spring application is executed.
 *
 * This is a normal scenario when working with Spring applications, and the warning does not
 * indicate an issue with your code or project setup.
 */
