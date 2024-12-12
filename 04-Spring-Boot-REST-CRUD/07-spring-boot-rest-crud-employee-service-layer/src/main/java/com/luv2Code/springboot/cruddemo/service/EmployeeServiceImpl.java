// Define the package for the service implementation
package com.luv2Code.springboot.cruddemo.service;

// Import necessary classes and annotations
import com.luv2Code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2Code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation class for EmployeeService.
 *
 * This class provides the business logic for managing employees by delegating
 * data access operations to the DAO layer. The @Service annotation marks this
 * class as a Spring-managed service component.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Define a field for the EmployeeDAO
    private EmployeeDAO employeeDAO;

    /**
     * Constructor for injecting the EmployeeDAO dependency.
     *
     * The @Autowired annotation is used for constructor injection. This ensures
     * that the required DAO is automatically provided by Spring.
     *
     * @param employeeDAO The EmployeeDAO instance to be injected.
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * Retrieves a list of all employees.
     *
     * This method delegates the task of fetching employee data to the DAO layer.
     * It implements the method declared in the EmployeeService interface.
     *
     * @return A list of Employee objects.
     */
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll(); // Delegate the call to the DAO
    }
}
