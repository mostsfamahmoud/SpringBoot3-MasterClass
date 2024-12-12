// Define the package for this class
package com.luv2code.springCoreDemo.common;

// Import the necessary Spring Framework annotation for defining a component
import org.springframework.stereotype.Component;

/**
 * This class is a Spring component that implements the Coach interface.
 * It provides a specific implementation for the getDailyWorkout() method.
 */

public class SwimCoach implements Coach {

    public SwimCoach()
    {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    /**
     * Provides a specific implementation of the getDailyWorkout() method
     * as required by the Coach interface. This method returns a daily workout
     * routine specific to Swim training.
     *
     * @return A String containing the daily workout routine for a Swim coach
     */
    @Override
    public String getDailyWorkout() {
        // Return a specific workout instruction
        return "Swim 1000 meters as a Warm-Up.";
    }
}
