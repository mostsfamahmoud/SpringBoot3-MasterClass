// Define the package for this class
package com.luv2code.springCoreDemo.common;

// Import the necessary Spring Framework annotation for defining a component
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This class is a Spring component that implements the Coach interface.
 * It provides a specific implementation for the getDailyWorkout() method,
 * offering a workout routine focused on cricket training.
 *
 * The @Component annotation marks this class as a Spring-managed bean,
 * allowing Spring to automatically detect and register it during component scanning.
 *
 * The @Scope annotation is used to define the bean's scope. In this case, it's set
 * to "prototype", meaning a new instance of this class will be created each time it
 * is requested from the Spring container.
 */
@Component  // Marks this class as a Spring component for dependency injection
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  // Ensures the bean has a prototype scope
public class CricketCoach implements Coach {

    /**
     * Constructor for CricketCoach.
     * Prints a message to the console when the class is instantiated.
     * This is useful for diagnostics and tracing the lifecycle of the bean,
     * especially when using prototype scope.
     */
    public CricketCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    /**
     * Provides a specific implementation of the getDailyWorkout() method
     * as required by the Coach interface. This method returns a daily workout
     * routine tailored to cricket training.
     *
     * @return A String containing the daily workout routine for a cricket coach,
     *         which in this case is "Practice fast bowling for 15 minutes."
     */
    @Override
    public String getDailyWorkout() {
        // Return a specific workout instruction for cricket training
        return "Practice fast bowling for 15 minutes.";
    }
}
