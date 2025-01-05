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
     * Spring Data JPA will parse the method name
     * Looks for a specific format and pattern
     * Creates appropriate query ... behind the scenes
     * Query:
     *  Select *
     *  From Employee
     *  Order By lastName ASC
     *
     * follow this link for more: www.luv2code.com/query-methods
     * */
    List<Employee> findAllByOrderByLastNameAsc();
}
