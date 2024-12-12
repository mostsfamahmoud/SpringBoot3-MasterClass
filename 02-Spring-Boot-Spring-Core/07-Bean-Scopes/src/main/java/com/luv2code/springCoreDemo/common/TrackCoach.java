// Define the package for this class
package com.luv2code.springCoreDemo.common;

// Import the necessary Spring Framework annotation for defining a component
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * This class is a Spring component that implements the Coach interface.
 * It provides a specific implementation for the getDailyWorkout() method,
 * offering a workout routine tailored to track training.

 * The @Component annotation indicates that this class is a Spring-managed bean,
 * allowing Spring to automatically detect and register it as a bean during
 * component scanning. It can then be injected into other classes where needed.

 * The @Lazy annotation ensures that this bean is initialized lazily, i.e.,
 * it will not be instantiated at startup but only when it is first requested
 * or required for dependency injection.
 */
@Component  // Marks this class as a Spring component for dependency injection
public class TrackCoach implements Coach {

    /**
     * Constructor for TrackCoach.
     * Prints a message to the console when the class is instantiated.
     * This is useful for understanding the lifecycle and instantiation timing
     * when using lazy initialization.
     */
    public TrackCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    /**
     * Provides a specific implementation of the getDailyWorkout() method
     * as required by the Coach interface. This method returns a daily workout
     * routine focused on track training.
     *
     * @return A String containing the daily workout routine for a track coach,
     *         which in this case is "Run a Hard 5k!"
     */
    @Override
    public String getDailyWorkout() {
        // Return a specific workout instruction for track training
        return "Run a Hard 5k!";
    }
}
