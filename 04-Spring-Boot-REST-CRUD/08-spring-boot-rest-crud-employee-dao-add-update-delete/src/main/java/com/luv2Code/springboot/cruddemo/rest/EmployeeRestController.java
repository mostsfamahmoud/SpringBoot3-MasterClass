// Define the package for the REST controller
package com.luv2Code.springboot.cruddemo.rest;

// Import necessary classes and annotations
import com.luv2Code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2Code.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for managing Employee-related endpoints.
 *
 * The @RestController annotation indicates that this class handles RESTful requests
 * and directly returns data in JSON format.
 *
 * The @RequestMapping annotation at the class level sets the base path for all endpoints in this controller.
 */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // Define a field for the EmployeeDAO
    private EmployeeDAO employeeDAO;

    /**
     * Constructor to inject the EmployeeDAO.
     *
     * This is a "quick and dirty" approach for injecting the DAO dependency
     * using constructor injection.
     *
     * @param employeeDAO The EmployeeDAO instance to be injected.
     */
    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * Endpoint to retrieve all employees.
     *
     * The @GetMapping annotation maps HTTP GET requests to this method. This method
     * returns a list of employees as JSON.
     *
     * @return A list of Employee objects.
     */
    @GetMapping("/employees")
    public List<Employee> findAll() {
        // Call the DAO's findAll() method to retrieve employee data
        return employeeDAO.findAll();
    }
}
