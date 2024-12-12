// Define the package for the service implementation
package com.luv2Code.springboot.cruddemo.service;

// Import necessary classes and annotations

import com.luv2Code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2Code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation class for EmployeeService.
 * <p>
 * This class provides the business logic for managing employees by delegating
 * data access operations to the DAO layer. The @Service annotation marks this
 * class as a Spring-managed service component.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Define a field for the EmployeeDAO to handle data access operations
    private EmployeeDAO employeeDAO;

    /**
     * Constructor for injecting the EmployeeDAO dependency.
     * <p>
     * The @Autowired annotation ensures that Spring automatically injects
     * the EmployeeDAO bean into this service class.
     *
     * @param employeeDAO The EmployeeDAO instance to be injected.
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * Retrieves a list of all employees.
     * <p>
     * This method delegates the task of fetching employee data to the DAO layer
     * by calling the `findAll` method of EmployeeDAO. It implements the method
     * declared in the EmployeeService interface.
     *
     * @return A list of Employee objects.
     */
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll(); // Delegate the call to the DAO
    }

    /**
     * Retrieves an employee by their ID.
     * <p>
     * This method fetches a specific employee from the DAO layer based on their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The Employee object with the specified ID, or null if not found.
     */
    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id); // Delegate the call to the DAO
    }

    /**
     * Saves or updates an employee in the database.
     * <p>
     * This method delegates the save operation to the DAO layer.
     * The @Transactional annotation ensures that the operation is executed within a transactional context.
     *
     * @param employee The Employee object to save or update.
     * @return The Employee object after it has been saved or updated in the database.
     */
    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee); // Delegate the call to the DAO
    }

    /**
     * Deletes an employee by their ID.
     * <p>
     * This method delegates the delete operation to the DAO layer.
     * The @Transactional annotation ensures that the operation is executed within a transactional context.
     *
     * @param id The ID of the employee to delete.
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id); // Delegate the call to the DAO
    }
}
