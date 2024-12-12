// Define the package for this class
package com.luv2code.springCoreDemo;

// Import necessary Spring Framework classes for running a Spring Boot application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Boot application.
 * This class is responsible for bootstrapping and launching the Spring application.

 * The @SpringBootApplication annotation enables key Spring Boot features like:
 * - Auto-configuration: Automatically configures Spring beans based on classpath settings.
 * - Component scanning: Scans the specified packages (or default package) to detect and register Spring components.
 * - Enables Spring Boot features such as property configuration, web support, etc.

 * You can customize the packages to be scanned by using the "scanBasePackages" option
 * to define the packages where Spring should search for components.
 * Example:
 *
 * @SpringBootApplication(
 *      scanBasePackages = {"com.luv2Code.springCoreDemo", "com.luv2Code.util"}
 * )
 *
 * This setup would scan both the "com.luv2Code.springCoreDemo" and "com.luv2Code.util" packages.
 */
// The @SpringBootApplication annotation tells Spring Boot that this is the main configuration class
// Uncomment the following block if you need to specify custom packages for scanning components

/*
@SpringBootApplication(
    scanBasePackages = {"com.luv2Code.springCoreDemo", "com.luv2Code.util"}
)
*/

@SpringBootApplication  // This is the default setup with auto-configuration and component scanning for the current package and sub-packages
public class SpringCoreDemoApplication {

	/**
	 * The main method that serves as the entry point for the Java application.
	 * This method starts the Spring application using Spring Boot's SpringApplication.run() method.
	 *
	 * @param args - Command-line arguments passed to the application at runtime (if any).
	 */
	public static void main(String[] args) {
		// Launch the Spring Boot application by initializing the Spring application context
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}
}
