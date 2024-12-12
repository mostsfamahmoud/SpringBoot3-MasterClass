// Define the package for the Data Access Object (DAO) implementation
package com.luv2Code.springboot.cruddemo.dao;

// Import necessary classes and annotations

import com.luv2Code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the EmployeeDAO interface using JPA and the EntityManager.
 * <p>
 * This class handles the CRUD operations for Employee entities in the database
 * using JPA. It is annotated with @Repository to indicate that it is a component
 * responsible for interacting with the database.
 */
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // Define a field for the EntityManager to manage database interactions
    private EntityManager entityManager;

    /**
     * Constructor for dependency injection of the EntityManager.
     * <p>
     * The @Autowired annotation ensures that Spring automatically injects an
     * EntityManager bean into this class.
     *
     * @param entityManager The EntityManager instance used for database operations.
     */
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retrieve all employees from the database.
     * <p>
     * This method creates and executes a JPA TypedQuery to fetch all Employee entities.
     *
     * @return A list of Employee objects.
     */
    @Override
    public List<Employee> findAll() {
        // Create a query to fetch all Employee entities
        // Note: The query uses the name of the Entity class ("Employee"), not the database table name
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute the query and get the results as a list
        List<Employee> employees = employeeTypedQuery.getResultList();

        // Return the result list
        return employees;
    }

    /**
     * Retrieve an employee by their ID.
     * <p>
     * This method uses the EntityManager's find method to locate the Employee entity
     * with the specified ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The Employee object with the specified ID, or null if not found.
     */
    @Override
    public Employee findById(int id) {
        // Use EntityManager to find the employee by ID
        Employee employee = entityManager.find(Employee.class, id);

        // Return the found employee
        return employee;
    }

    /**
     * Save or update an employee in the database.
     * <p>
     * If the employee's ID is 0 (not yet persisted), this method performs an insert operation.
     * If the ID is not 0, it performs an update operation.
     *
     * @param employee The Employee object to save or update.
     * @return The Employee object after saving or updating in the database.
     */
    @Override
    public Employee save(Employee employee) {
        // Use EntityManager's merge method to save or update the employee
        Employee dbEmployee = entityManager.merge(employee);

        // Return the saved or updated employee
        return dbEmployee;
    }

    /**
     * Delete an employee by their ID.
     * <p>
     * This method finds the Employee entity with the specified ID and removes it
     * from the database.
     *
     * @param id The ID of the employee to delete.
     */
    @Override
    public void deleteById(int id) {
        // Find the employee by ID
        Employee employee = entityManager.find(Employee.class, id);

        // Remove the employee from the database
        entityManager.remove(employee);
    }
}
