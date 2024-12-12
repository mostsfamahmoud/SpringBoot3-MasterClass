// Define the package for this class
package com.springboot.myCoolApp;

// Import necessary Spring Boot classes for starting the application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class that starts the Spring Boot application.
 * Responsible for bootstrapping the app and providing auto-configuration.
 * The @SpringBootApplication annotation marks this as the entry point.
 */
@SpringBootApplication  // Marks this class as a Spring Boot application and enables auto-configuration
public class MyCoolApplication {

	/**
	 * The main method is the entry point of the Java application.
	 * It uses SpringApplication.run() to start the Spring Boot application.
	 *
	 * @param args Command-line arguments that can be passed when starting the application
	 */
	public static void main(String[] args) {
		// This command starts the entire Spring Boot application

		/**
		 * This method sets up the default Spring application context,
		 * initializes all necessary Spring beans, and starts the embedded server (e.g., Tomcat) to listen for incoming web requests.
		 */
		/**
		 * The SpringApplication.run() method also takes care of various other things such as:
		 	* Setting up the application context.
		 	* Configuring Spring Beans and Dependency Injection.
		 	* Starting the embedded web server (e.g., Tomcat) to serve HTTP requests.
		 	* Managing the entire Spring lifecycle during the runtime of the application.*/
		SpringApplication.run(MyCoolApplication.class, args);
	}
}
