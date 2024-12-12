// Define the package for this interface
package com.luv2code.springCoreDemo;

/**
 * This interface defines the contract for a Coach.
 * Any class implementing this interface will provide its own
 * implementation of the getDailyWorkout() method.
 *
 * The purpose of this interface is to provide a consistent way
 * to retrieve a daily workout routine, regardless of the specific
 * type of Coach implementation.
 */
public interface Coach {

    /**
     * Method signature for getting a daily workout routine.
     * Classes implementing this interface must provide their own
     * implementation of this method to return a workout suggestion.
     *
     * @return A String containing the daily workout routine
     */
    String getDailyWorkout();
}
