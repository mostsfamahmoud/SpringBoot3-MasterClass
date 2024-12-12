// Define the package for the REST controller
package com.luv2Code.springboot.cruddemo.rest;

// Import necessary classes and annotations

import com.luv2Code.springboot.cruddemo.entity.Employee;
import com.luv2Code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Employee-related endpoints.
 * <p>
 * The @RestController annotation marks this class as a Spring REST controller,
 * which processes HTTP requests and returns data in JSON format.
 * <p>
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
     * <p>
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
     * <p>
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

    /**
     * Endpoint to retrieve a specific employee by their ID.
     * <p>
     * The @GetMapping annotation maps HTTP GET requests to this method. It uses the
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return The Employee object with the given ID, or a RuntimeException if not found.
     * @PathVariable annotation to extract the `employeeId` from the URL path.
     */
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        // Retrieve the employee by ID from the service layer
        Employee employee = employeeService.findById(employeeId);

        // Check if the employee exists; if not, throw an exception
        if (employee == null) {
            throw new RuntimeException("Employee ID not found - " + employeeId);
        }

        // Return the employee data
        return employee;
    }

    /**
     * Endpoint to add a new employee.
     * <p>
     * The @PostMapping annotation maps HTTP POST requests to this method.
     * It is used to create a new employee and save it to the database.
     * <p>
     * The @RequestBody annotation binds the incoming JSON payload to the
     * Employee object.
     *
     * @param employee The Employee object received in the HTTP request body.
     * @return The saved Employee object with its database-assigned ID.
     */
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // Set the ID to 0 to force the operation as a save (insert) rather than an update.
        // This is to ensure that even if a client provides an ID in the JSON payload,
        // it will be ignored, and the object is treated as a new entity.
        employee.setId(0);

        // Delegate the save operation to the service layer and capture the saved object.
        Employee dbEmployee = employeeService.save(employee);

        // Return the saved Employee object.
        return dbEmployee;
    }

}
