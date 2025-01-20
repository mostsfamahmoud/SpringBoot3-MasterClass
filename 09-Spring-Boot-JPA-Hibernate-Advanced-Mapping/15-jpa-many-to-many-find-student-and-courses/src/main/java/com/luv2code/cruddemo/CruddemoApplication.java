package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
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

            // createCourseAndStudents(appDAO);
            // getCourseWithStudents(appDAO);
            getStudentWithCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
        };
    }

//    private void addMoreCoursesForStudent(AppDAO appDAO) {
//
//        // Specify the ID of the course to retrieve
//        int studentId = 2;
//
//        // Fetch the course and its reviews using a custom DAO method
//        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
//
//        // Create new courses
//        Course course1 = new Course("Python for data analysis");
//        Course course2 = new Course("Game Development");
//
//        // Add courses to the student
//        student.addCourse(course1);
//        student.addCourse(course2);
//
//        // Save the course and associated students
//        System.out.println("Updating the student: " + student);
//        System.out.println("Associated courses: " + student.getCourses());
//
//        appDAO.update(student);
//
//        System.out.println("DONE !");
//
//    }

    private void getStudentWithCourses(AppDAO appDAO) {

        // Specify the ID of the course to retrieve
        int studentId = 2;

        // Fetch the course and its reviews using a custom DAO method
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);

        // Display the course details
        System.out.println("Student Details: " + student.toString());

        // Display the list of associated students
        System.out.println("Associated Courses: " + student.getCourses());

        System.out.println("Data retrieval complete!");
    }

    private void getCourseWithStudents(AppDAO appDAO) {

        // Specify the ID of the course to retrieve
        int courseId = 10;

        // Fetch the course and its reviews using a custom DAO method
        Course course = appDAO.findCourseAndStudentsByCourseId(courseId);

        // Display the course details
        System.out.println("Course Details: " + course.toString());

        // Display the list of associated students
        System.out.println("Associated Students: " + course.getStudents());

        System.out.println("Data retrieval complete!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        // Create a course
        Course course = new Course("Manara - Modern JavaScript");

        // Create the students
        Student student1 =
                new Student("John", "Doe", "john@luv2code.com");

        Student student2 =
                new Student("Mostafa", "Nasser", "mostafa@luv2code.com");

        // Add students to the course
        course.addStudent(student1);
        course.addStudent(student2);

        // Save the course and associated students
        System.out.println("Saving the course: " + course);
        System.out.println("Associated students: " + course.getStudents());

        appDAO.save(course);

        System.out.println("DONE !");

    }

    /**
     * Deletes a course along with its associated reviews from the database.
     *
     * @param appDAO The Data Access Object to interact with the persistence layer.
     */
    private void deleteCourseWithReviews(AppDAO appDAO) {
        // Specify the ID of the course to delete
        int id = 10;
        System.out.println("Initiating deletion of Course with ID: " + id);

        // Perform deletion of the Course entity, including all associated reviews.
        // The deletion is supported by the cascading configuration in the Course entity.
        appDAO.deleteCourseById(id);

        System.out.println("Course and its associated reviews have been successfully deleted!");
    }

    /**
     * Retrieves a course along with its associated reviews from the database.
     * <p>
     * This method fetches both the course and its reviews in a single query using JPQL's JOIN FETCH,
     * avoiding lazy loading issues when accessing the reviews.
     *
     * @param appDAO The Data Access Object to interact with the persistence layer.
     */
    private void getCourseWithReviews(AppDAO appDAO) {
        // Specify the ID of the course to retrieve
        int courseId = 10;

        // Fetch the course and its reviews using a custom DAO method
        Course course = appDAO.findCourseAndReviewsByCourseId(courseId);

        // Display the course details
        System.out.println("Course Details: " + course.toString());

        // Display the list of associated reviews
        System.out.println("Associated Reviews: " + course.getReviews());

        System.out.println("Data retrieval complete!");
    }

    /**
     * Creates a new course and adds multiple reviews to it.
     * <p>
     * This method demonstrates how to use the cascading feature to persist both
     * the course and its associated reviews in a single operation.
     *
     * @param appDAO The Data Access Object to interact with the persistence layer.
     */
    private void createCourseWithReviews(AppDAO appDAO) {
        // Create a new Course object
        Course course = new Course("Udemy - Master SpringBoot 3");

        // Add multiple reviews to the course using the addReview method
        course.addReview(new Review("Great Course ... Loved it!"));
        course.addReview(new Review("Cool Course ... Job Well-Done."));
        course.addReview(new Review("What a dumb course, You are an idiot."));

        // Save the course to the database.
        // The associated reviews are also saved due to the CascadeType.ALL setting.
        System.out.println("Saving the course and its reviews...");
        System.out.println("Course Details: " + course);
        System.out.println("Associated Reviews: " + course.getReviews());

        // Persist the course and its reviews
        appDAO.save(course);

        System.out.println("Course creation with reviews is complete!");
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
