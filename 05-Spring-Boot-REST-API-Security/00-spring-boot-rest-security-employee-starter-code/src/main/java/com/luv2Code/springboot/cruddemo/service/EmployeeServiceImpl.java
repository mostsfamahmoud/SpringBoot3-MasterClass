// Define the package for the service implementation
package com.luv2Code.springboot.cruddemo.service;

// Import necessary classes and annotations
import com.luv2Code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2Code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for EmployeeService.
 * <p>
 * This class provides the business logic for managing employees by delegating
 * data access operations to the repository layer. The @Service annotation marks this
 * class as a Spring-managed service component.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Define a field for the EmployeeRepository to handle data access operations
    private EmployeeRepository employeeRepository;

    /**
     * Constructor for injecting the EmployeeRepository dependency.
     * <p>
     * The @Autowired annotation ensures that the required repository is automatically
     * provided by Spring.
     *
     * @param employeeRepository The EmployeeRepository instance to be injected.
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieves a list of all employees.
     * <p>
     * This method fetches all employee records by delegating the call to the repository layer.
     *
     * @return A list of Employee objects.
     */
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Retrieves an employee by their ID.
     * <p>
     * This method fetches a specific employee from the repository layer based on their ID.
     * If the employee is not found, it throws a RuntimeException.
     *
     * @param id The ID of the employee to retrieve.
     * @return The Employee object with the specified ID.
     */
    @Override
    public Employee findById(int id) {
        // Use Optional to handle the potential absence of an employee
        Optional<Employee> result = employeeRepository.findById(id);

        // Check if the employee exists; if not, throw an exception
        if (result.isEmpty()) {
            throw new RuntimeException("Employee ID not found - " + id);
        }

        // Return the found employee
        return result.get();
    }

    /**
     * Saves or updates an employee in the database.
     * <p>
     * If the employee's ID is not set (or is 0), a new employee record is created.
     * Otherwise, the existing record is updated.
     *
     * @param employee The Employee object to save or update.
     * @return The Employee object after it has been saved or updated.
     */
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Deletes an employee by their ID.
     * <p>
     * If the employee with the specified ID exists, it is removed from the database.
     *
     * @param id The ID of the employee to delete.
     */
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
