// Define the package for this class
package com.luv2code.springCoreDemo.config;

// Import necessary classes for configuration and bean management
import com.luv2code.springCoreDemo.common.Coach;
import com.luv2code.springCoreDemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a Spring Configuration class used for defining beans manually.
 * It is marked with the @Configuration annotation to indicate that it contains
 * methods for bean definitions, which will be managed by the Spring container.
 */
@Configuration  // Marks this class as a source of bean definitions for the Spring container
public class SportConfig {

    /**
     * Defines a bean for SwimCoach.
     * The @Bean annotation indicates that this method returns a bean to be managed by Spring's IoC container.
     * The bean's ID will default to the method name "swimCoach" unless specified explicitly.
     *
     * In this case, the bean ID is explicitly set to "aquatic" using the @Bean annotation's value attribute.
     * This allows the SwimCoach bean to be referred to by the "aquatic" ID within the Spring context.
     *
     * @Bean UseCase:
     * The @Bean annotation is often used when we need to expose a third-party class or library
     * as a Spring bean, allowing it to be managed by the Spring container.
     *
     * @return An instance of SwimCoach as a bean, implementing the Coach interface.
     */
    @Bean("aquatic")  // Specifies the bean ID as "aquatic" instead of the default "swimCoach"
    public Coach swimCoach() {
        // Returns a new instance of SwimCoach to be registered as a bean in the Spring context
        return new SwimCoach();
    }
}
