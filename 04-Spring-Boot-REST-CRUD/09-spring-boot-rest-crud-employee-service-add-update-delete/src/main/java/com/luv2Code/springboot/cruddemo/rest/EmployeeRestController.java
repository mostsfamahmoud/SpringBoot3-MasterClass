// Define the package for the REST controller
package com.luv2Code.springboot.cruddemo.rest;

// Import necessary classes and annotations
import com.luv2Code.springboot.cruddemo.entity.Employee;
import com.luv2Code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for managing Employee-related endpoints.
 *
 * The @RestController annotation marks this class as a Spring REST controller,
 * which processes HTTP requests and returns data in JSON format.
 *
 * The @RequestMapping annotation at the class level sets the base URL for all
 * endpoints in this controller.
 */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // Define a field for the EmployeeService
    private EmployeeService employeeService;

    /**
     * Constructor for injecting the EmployeeService dependency.
     *
     * The @Autowired annotation is used to inject the service implementation automatically.
     * This ensures that the EmployeeService is available for handling business logic.
     *
     * @param employeeService The EmployeeService instance to be injected.
     */
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint to retrieve all employees.
     *
     * The @GetMapping annotation maps HTTP GET requests to this method. When this
     * endpoint is accessed, it fetches a list of all employees and returns it as JSON.
     *
     * @return A list of Employee objects.
     */
    @GetMapping("/employees")
    public List<Employee> findAll() {
        // Delegate the call to the service layer to retrieve employee data
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employee_id}")
    public Employee findById(@PathVariable int employee_id)
    {
        return employeeService.findById(employee_id);
    }
}
