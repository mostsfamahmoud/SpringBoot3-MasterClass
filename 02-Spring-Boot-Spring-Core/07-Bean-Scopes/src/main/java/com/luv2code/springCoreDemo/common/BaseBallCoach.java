// Define the package for this class
package com.luv2code.springCoreDemo.common;

// Import the necessary Spring Framework annotation for defining a component
import org.springframework.stereotype.Component;

/**
 * This class is a Spring component that implements the Coach interface.
 * It provides a specific implementation for the getDailyWorkout() method.
 *
 * The @Component annotation indicates that this class is a Spring-managed bean,
 * allowing Spring to automatically detect and register it as a bean during
 * component scanning.
 */
@Component  // Marks this class as a Spring component for dependency injection
public class BaseBallCoach implements Coach {

    public BaseBallCoach()
    {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    /**
     * Provides a specific implementation of the getDailyWorkout() method
     * as required by the Coach interface. This method returns a daily workout
     * routine specific to BaseBall training.
     *
     * @return A String containing the daily workout routine for a BaseBall coach
     */
    @Override
    public String getDailyWorkout() {
        // Return a specific workout instruction
        return "Spend 30 Minutes in Batting Practice.";
    }
}
