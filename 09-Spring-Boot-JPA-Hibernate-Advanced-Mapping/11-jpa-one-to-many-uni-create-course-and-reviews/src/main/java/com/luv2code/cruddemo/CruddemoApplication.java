package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
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
     * CommandLineRunner bean to execute database logic after the application context is loaded.
     *
     * @param appDAO DAO object for performing database operations.
     * @return A CommandLineRunner instance containing the tasks to execute.
     */
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {

            //createCourseWithReviews(appDAO);

            getCourseWithReviews(appDAO);
        };
    }

    private void getCourseWithReviews(AppDAO appDAO) {

        int courseId = 10;

        Course course = appDAO.findCourseAndReviewsByCourseId(courseId);

        System.out.println(course.toString());
        System.out.println("Associated Reviews: " + course.getReviews());

        System.out.println("DONE !");

    }

    private void createCourseWithReviews(AppDAO appDAO) {

        // Create a course
        Course course = new Course("Udemy - Master SpringBoot 3");

        // Add some reviews
        course.addReview(new Review("Great Course ... Loved it"));
        course.addReview(new Review("Cool Course ... Job Well-Done"));
        course.addReview(new Review("What a dumb course, You are an idiot"));

        // save the course and leverage the cascade_all
        System.out.println("Saving the course");
        System.out.println(course);
        System.out.println(course.getReviews());

        appDAO.save(course);

        System.out.println("DONE !");
    }

    /**
     * Deletes a course by its ID.
     *
     * @param appDAO DAO object for database operations.
     */
    private void deleteCourse(AppDAO appDAO) {
        int id = 11; // ID of the course to delete
        System.out.println("Deleting Course with ID: " + id);

        // Perform deletion
        appDAO.deleteCourseById(id);

        System.out.println("Course deletion completed!");
    }

    /**
     * Updates the title of an existing course.
     *
     * @param appDAO DAO object for database operations.
     */
    private void updateCourse(AppDAO appDAO) {
        int id = 10; // ID of the course to update
        System.out.println("Finding Course with ID: " + id);

        // Retrieve the course to be updated
        Course course = appDAO.findCourseById(id);

        // Update the course's title
        System.out.println("Updating Course with ID: " + id);
        course.setTitle("Quantum Computing");

        // Save the updated course
        appDAO.update(course);

        System.out.println("Course update completed!");
    }

    /**
     * Updates the last name of an existing instructor.
     *
     * @param appDAO DAO object for database operations.
     */
    private void updateInstructor(AppDAO appDAO) {
        int id = 1; // ID of the instructor to update
        System.out.println("Finding Instructor with ID: " + id);

        // Retrieve the instructor to be updated
        Instructor instructor = appDAO.findInstructorById(id);

        // Update the instructor's last name
        System.out.println("Updating Instructor with ID: " + id);
        instructor.setLastName("Nasser");

        // Save the updated instructor
        appDAO.update(instructor);

        System.out.println("Instructor update completed!");
    }

    /**
     * This method demonstrates how to use Join Fetch to retrieve an instructor along with their associated courses
     * in a single database query.
     *
     * @param appDAO An instance of the AppDAO class that interacts with the database.
     */
    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int id = 1; // ID of the instructor we want to find.
        System.out.println("Finding Instructor with ID: " + id);

        // Using Join Fetch in the query: This ensures both the instructor and their courses
        // are fetched in one database operation (avoiding lazy loading issues).
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

        // Output the retrieved instructor details.
        System.out.println("Instructor: " + instructor);

        // Output the associated courses of the instructor.
        System.out.println("Associated courses: " + instructor.getCourses());

        // Indicate the completion of the fetch operation.
        System.out.println("Fetch operation completed!");
    }


    /**
     * Retrieves and displays the courses associated with a specific instructor.
     * <p>
     * This method fetches an instructor by their ID, retrieves their associated courses,
     * and establishes the relationship between the instructor and the courses. The details
     * of the instructor and their courses are then printed to the console.
     *
     * @param appDAO The DAO object for performing database operations.
     */
    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 1; // ID of the instructor to fetch courses for
        System.out.println("Finding Instructor with ID: " + id);

        // Retrieve the instructor by ID
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);

        System.out.println("Finding courses for Instructor with ID: " + id);

        // Retrieve the list of courses associated with the instructor
        List<Course> courses = appDAO.findCoursesByInstructorId(id);

        // Establish the association between the instructor and the courses
        instructor.setCourses(courses);

        // Print the associated courses
        System.out.println("Associated Courses: " + instructor.getCourses());

        System.out.println("Fetch operation completed!");
    }


    /**
     * Fetches an Instructor along with their associated courses (EAGER fetch mode required).
     *
     * @param appDAO DAO for database operations.
     */
    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1; // ID of the instructor to fetch
        System.out.println("Fetching Instructor with ID: " + id);

        // Retrieve the instructor and print details
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        System.out.println("Associated Courses: " + instructor.getCourses());

        System.out.println("Fetch operation completed!");
    }

    /**
     * Creates an Instructor with associated courses and persists them in the database.
     *
     * @param appDAO DAO for database operations.
     */
    private void createInstructorWithCourses(AppDAO appDAO) {
        // Create an instructor and their detail
        Instructor instructor = new Instructor("Susan", "Darby", "susan@luvscode.com");
        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Swimming");
        instructor.setInstructorDetail(instructorDetail);

        // Set the association
        instructor.setInstructorDetail(instructorDetail);

        // Add courses to the instructor
        // Bi-directional association
        instructor.addCourse(new Course("Air Guitar - The Ultimate Guide"));
        instructor.addCourse(new Course("The Pinball Masterclass"));

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
        int id = 1;
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
