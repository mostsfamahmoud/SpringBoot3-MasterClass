// Define the package for the REST controller
package com.luv2Code.demo.rest;

// Import necessary classes and annotations
import com.luv2Code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @GetMapping("/students/{student_id}")
    public Student getStudent(@PathVariable int student_id) {
        // Return the student at the specified index
        return students.get(student_id);
    }
}
