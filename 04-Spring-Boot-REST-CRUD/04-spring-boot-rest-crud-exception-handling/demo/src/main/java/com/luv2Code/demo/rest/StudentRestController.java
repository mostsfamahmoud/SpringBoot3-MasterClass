// Define the package for the REST controller
package com.luv2Code.demo.rest;

// Import necessary classes and annotations
import com.luv2Code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for managing Student-related endpoints.
 *
 * The @RestController annotation indicates that this class handles RESTful requests
 * and directly returns data in JSON format. The @RequestMapping annotation at the
 * class level sets the base path for all endpoints in this controller.
 */
@RestController
@RequestMapping("/api")
public class StudentRestController {

    // A list to hold Student objects
    private List<Student> students = null;

    /**
     * Initializes the student data only once after the bean is constructed.
     *
     * The @PostConstruct annotation marks this method to be executed once the
     * dependency injection is complete. It is used to set up initial data.
     */
    @PostConstruct
    public void loadData() {
        // Create a new ArrayList to hold student data
        students = new ArrayList<>();

        // Add sample Student objects to the list
        students.add(new Student("Mostafa", "Mahmoud"));
        students.add(new Student("Mario", "Rosi"));
        students.add(new Student("Mary", "Smith"));
    }

    /**
     * Endpoint to retrieve the list of all students.
     *
     * The @GetMapping annotation maps HTTP GET requests to this method.
     *
     * @return A list of Student objects in JSON format.
     */
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students; // Return the list of students
    }

    /**
     * Endpoint to retrieve a specific student by their index in the list.
     *
     * The @PathVariable annotation binds the {student_id} placeholder in the
     * URL to the method parameter.
     *
     * @param student_id The index of the student in the list.
     * @return The Student object at the specified index.
     * @throws StudentNotFoundException if the student ID is invalid.
     */
    @GetMapping("/students/{student_id}")
    public Student getStudent(@PathVariable int student_id) throws StudentNotFoundException {
        // Check if the student_id is valid
        if (student_id >= students.size() || student_id < 0) {
            /** Step 3:
             * Throw an exception if the student ID is not found
             */
            throw new StudentNotFoundException("Student ID not found - " + student_id);
        }

        // Return the student at the specified index
        return students.get(student_id);
    }

    /** Step 4:
     * Exception handler for StudentNotFoundException.
     *
     * The @ExceptionHandler annotation maps this method to handle exceptions
     * of type StudentNotFoundException.
     *
     * @param exception The exception object.
     * @return A ResponseEntity containing the error response and HTTP status.
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
        // Create a StudentErrorResponse object with error details
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // Return the error response as a ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /** Step 4:
     * Generic exception handler for any other exceptions.
     *
     * The @ExceptionHandler annotation maps this method to handle all other exceptions.
     *
     * @param exception The exception object.
     * @return A ResponseEntity containing the error response and HTTP status.
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
        // Create a StudentErrorResponse object with error details
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // Return the error response as a ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
