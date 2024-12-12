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
 *
 * The @Repository annotation indicates that this class is a DAO component
 * that interacts with the database for CRUD operations.
 */
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // Define a field for the EntityManager to handle database operations
    private EntityManager entityManager;

    /**
     * Constructor for dependency injection of the EntityManager.
     *
     * The @Autowired annotation instructs Spring to automatically inject the EntityManager bean.
     *
     * @param entityManager The EntityManager instance for interacting with the database.
     */
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retrieve all employees from the database.
     *
     * This method uses JPA's TypedQuery to execute a query and fetch all Employee entities.
     *
     * @return A list of Employee objects.
     */
    @Override
    public List<Employee> findAll() {
        // Create a query to fetch all Employee entities
        // Note: The "from Employee" query uses the name of the Entity class, not the database table name
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute the query and retrieve the result list
        List<Employee> employees = employeeTypedQuery.getResultList();

        // Return the result list
        return employees;
    }
}
