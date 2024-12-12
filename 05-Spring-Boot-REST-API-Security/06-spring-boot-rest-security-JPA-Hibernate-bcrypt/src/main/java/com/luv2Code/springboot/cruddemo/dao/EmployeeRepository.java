// Define the package for the Data Access Object (DAO) interface
package com.luv2Code.springboot.cruddemo.dao;

// Import necessary classes
import com.luv2Code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
