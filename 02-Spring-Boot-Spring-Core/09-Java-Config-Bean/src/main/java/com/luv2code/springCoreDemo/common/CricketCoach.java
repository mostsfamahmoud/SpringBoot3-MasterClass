// Define the package for this class
package com.luv2code.springCoreDemo.common;

// Import necessary annotations and classes from Jakarta and Spring
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 * This class is a Spring component that implements the Coach interface.
 * It represents a cricket coach providing specific workout routines for cricket training.
 *
 * The @Component annotation registers this class as a Spring-managed bean, which allows Spring
 * to automatically detect and create an instance of this class during component scanning.
 */
@Component  // Marks this class as a Spring component for dependency injection
public class CricketCoach implements Coach {

    /**
     * Constructor for CricketCoach.
     * This constructor is invoked when the bean is created.
     * It prints a message for diagnostics and bean lifecycle tracing purposes.
     */
    public CricketCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }


    /**
     * Provides the daily workout routine for the cricket coach.
     * This method implements the getDailyWorkout() method from the Coach interface and returns
     * a specific instruction related to cricket training.
     *
     * @return A String containing the workout instruction: "Practice fast bowling for 15 minutes."
     */
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes.";
    }
}
