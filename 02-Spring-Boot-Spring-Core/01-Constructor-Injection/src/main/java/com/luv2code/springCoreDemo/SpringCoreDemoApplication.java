// Define the package for this class
package com.luv2code.springCoreDemo;

// Import necessary Spring Framework classes for running a Spring Boot application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Boot application.
 * This class is responsible for bootstrapping and launching the Spring application.
 *
 * The @SpringBootApplication annotation enables auto-configuration, component scanning,
 * and other Spring Boot features, making it easier to set up a Spring application.
 */
@SpringBootApplication  // Marks this class as the main configuration class for the Spring Boot application
public class SpringCoreDemoApplication {

	/**
	 * The main method that serves as the entry point of the Java application.
	 * It uses Spring Boot's SpringApplication.run() method to launch the application.
	 *
	 * @param args - Command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		// Run the Spring Boot application
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}
}
