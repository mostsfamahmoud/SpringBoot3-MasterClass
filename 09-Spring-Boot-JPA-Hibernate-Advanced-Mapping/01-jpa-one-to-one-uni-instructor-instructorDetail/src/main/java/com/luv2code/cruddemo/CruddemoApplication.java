package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Entry point for the Spring Boot application.
 * <p>
 * This class contains the main method for running the application and a
 * CommandLineRunner bean for executing specific logic after the application context is loaded.
 */
@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        // Bootstraps the application by creating the Spring application context
        SpringApplication.run(CruddemoApplication.class, args);
    }

    /**
     * Bean that executes after all Spring Beans have been loaded into the application context.
     * <p>
     * This method is useful for performing initialization or testing tasks.
     *
     * @param appDAO The DAO object for interacting with the database.
     * @return A CommandLineRunner instance that executes the specified logic.
     */
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
            // Uncomment one of the following methods to test functionality:
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            deleteInstructor(appDAO);
        };
    }

    /**
     * Deletes an instructor by ID from the database.
     * <p>
     * This operation also deletes the associated InstructorDetail object
     * due to the CascadeType.ALL configuration in the Instructor entity.
     *
     * @param appDAO The DAO object for interacting with the database.
     */
    private void deleteInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Deleting Instructor with id: " + id);

        appDAO.deleteInstructorById(id);

        System.out.println("DONE!");
    }

    /**
     * Retrieves an instructor by ID from the database and prints its details,
     * including the associated InstructorDetail object.
     *
     * @param appDAO The DAO object for interacting with the database.
     */
    private void findInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding Instructor with id: " + id);

        // Retrieve the instructor
        Instructor instructor = appDAO.findInstructorById(id);

        // Print the instructor and associated details
        System.out.println(instructor.toString());
        System.out.println("Associated InstructorDetail: " + instructor.getInstructorDetail().toString());
    }

    /**
     * Creates a new instructor and saves it to the database.
     * <p>
     * This operation also saves the associated InstructorDetail object
     * due to the CascadeType.ALL configuration in the Instructor entity.
     *
     * @param appDAO The DAO object for interacting with the database.
     */
    private void createInstructor(AppDAO appDAO) {
        // Uncomment the following block to test creating a different instructor:
        /*
        Instructor instructor =
                new Instructor(
                        "Mostafa",
                        "Mahmoud",
                        "mostafa@luvscode.com"
                );

        InstructorDetail instructorDetail =
                new InstructorDetail(
                        "https://www.youtube.com/",
                        "Coding"
                );
        */

        // Create a new instructor
        Instructor instructor =
                new Instructor(
                        "Chad",
                        "Darby",
                        "chad@luvscode.com"
                );

        // Create a new InstructorDetail
        InstructorDetail instructorDetail =
                new InstructorDetail(
                        "https://www.youtube.com/",
                        "Teaching"
                );

        // Associate the instructor with the instructor detail
        instructor.setInstructorDetail(instructorDetail);

        // Save the instructor (and the associated details due to cascading)
        System.out.println("Saving Instructor: " + instructor);
        appDAO.save(instructor);
        System.out.println("DONE!");
    }
}
