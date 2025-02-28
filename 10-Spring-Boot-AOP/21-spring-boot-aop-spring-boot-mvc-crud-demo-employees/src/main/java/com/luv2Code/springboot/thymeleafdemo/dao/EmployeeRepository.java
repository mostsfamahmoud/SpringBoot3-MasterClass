// Define the package for the Data Access Object (DAO) interface
package com.luv2Code.springboot.thymeleafdemo.dao;

// Import necessary classes

import com.luv2Code.springboot.thymeleafdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Employee entity.
 * <p>
 * This interface extends JpaRepository, which provides built-in CRUD operations
 * for the Employee entity without requiring explicit method implementation.
 * JpaRepository comes with default implementations for common database operations.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // No need to write any code here or even an implementation class
    // JpaRepository provides methods like:
    // - findAll()
    // - findById(ID id)
    // - save(S entity)
    // - deleteById(ID id)

    /**
     * Method to retrieve all Employee entities ordered by their last names in ascending order.
     * <p>
     * Spring Data JPA will parse the method name to:
     * 1. Identify the query's purpose based on the method name structure.
     * 2. Automatically generate the query at runtime.
     * <p>
     * Query generated behind the scenes:
     * SELECT *
     * FROM Employee
     * ORDER BY lastName ASC
     * <p>
     * For more details on query method naming conventions, visit:
     * www.luv2code.com/query-methods
     */
    List<Employee> findAllByOrderByLastNameAsc();
}
