package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point for the Spring Boot application.
 * <p>
 * Contains the main method for application startup and a CommandLineRunner bean
 * for executing database operations during runtime.
 */
@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        // Bootstraps the application by creating the Spring application context
        SpringApplication.run(CruddemoApplication.class, args);
    }

    /**
     * Bean to execute logic after the application context is loaded.
     *
     * @param appDAO DAO for database operations.
     * @return CommandLineRunner instance for executing specified tasks.
     */
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // Uncomment one of the following methods to test specific functionality:
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            // deleteInstructor(appDAO);
            // findInstructorDetail(appDAO);
            // deleteInstructorDetail(appDAO);
            // createInstructorWithCourses(appDAO);
            findInstructorWithCourses(appDAO); // Works in EAGER Fetch mode only
        };
    }

    /**
     * Fetches an Instructor along with their associated courses (EAGER fetch mode required).
     *
     * @param appDAO DAO for database operations.
     */
    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1;
        System.out.println("Fetching Instructor with ID: " + id);

        // Retrieve and display details
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        System.out.println("Associated courses: " + instructor.getCourses());

        System.out.println("Fetch operation completed!");
    }

    /**
     * Creates an Instructor with associated courses and persists them in the database.
     *
     * @param appDAO DAO for database operations.
     */
    private void createInstructorWithCourses(AppDAO appDAO) {
        // Create Instructor and InstructorDetail objects
        Instructor instructor = new Instructor("Susan", "Darby", "susan@luvscode.com");
        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Swimming");

        // Set the association
        instructor.setInstructorDetail(instructorDetail);

        // Create a list of courses and associate them with the instructor
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Air Guitar - The Ultimate Guide"));
        courses.add(new Course("The Pinball Masterclass"));
        courses.forEach(instructor::addCourse); // Bi-directional association

        // Save the instructor
        // Courses will also be saved due to CascadeType.PERSIST
        System.out.println("Saving Instructor: " + instructor);
        System.out.println("Courses: " + instructor.getCourses());
        appDAO.save(instructor);

        System.out.println("Instructor with courses saved successfully!");
    }

    /**
     * Deletes an InstructorDetail by ID and breaks its association with the Instructor.
     *
     * @param appDAO DAO for database operations.
     */
    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 3;
        System.out.println("Deleting InstructorDetail with ID: " + id);

        // Perform deletion
        appDAO.deleteInstructorDetailById(id);

        System.out.println("InstructorDetail deletion completed!");
    }

    /**
     * Finds and prints details of an InstructorDetail and its associated Instructor.
     *
     * @param appDAO DAO for database operations.
     */
    private void findInstructorDetail(AppDAO appDAO) {
        int id = 1;
        System.out.println("Fetching InstructorDetail with ID: " + id);

        // Retrieve and display details
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("InstructorDetail: " + instructorDetail);
        System.out.println("Associated Instructor: " + instructorDetail.getInstructor());

        System.out.println("Fetch operation completed!");
    }

    /**
     * Deletes an Instructor by ID along with its associated InstructorDetail.
     *
     * @param appDAO DAO for database operations.
     */
    private void deleteInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Deleting Instructor with ID: " + id);

        // Perform deletion
        appDAO.deleteInstructorById(id);

        System.out.println("Instructor deletion completed!");
    }

    /**
     * Finds and prints details of an Instructor and its associated InstructorDetail.
     *
     * @param appDAO DAO for database operations.
     */
    private void findInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Fetching Instructor with ID: " + id);

        // Retrieve and display details
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        System.out.println("Associated InstructorDetail: " + instructor.getInstructorDetail());

        System.out.println("Fetch operation completed!");
    }

    /**
     * Creates and saves a new Instructor along with an InstructorDetail.
     *
     * @param appDAO DAO for database operations.
     */
    private void createInstructor(AppDAO appDAO) {
        System.out.println("Creating a new Instructor...");

        // Uncomment to test different data
        /*
        Instructor instructor = new Instructor("Mostafa", "Mahmoud", "mostafa@luvscode.com");
        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Coding");
        */

        // Create Instructor and InstructorDetail objects
        Instructor instructor = new Instructor("Chad", "Darby", "chad@luvscode.com");
        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Teaching");

        // Set the association
        instructor.setInstructorDetail(instructorDetail);

        // Save the entities
        System.out.println("Saving Instructor: " + instructor);
        appDAO.save(instructor);

        System.out.println("Instructor creation completed!");
    }
}
