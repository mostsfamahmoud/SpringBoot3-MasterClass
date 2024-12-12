// Define the package for the Data Access Object (DAO) interface
package com.luv2Code.springboot.cruddemo.dao;

// Import necessary classes
import com.luv2Code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository interface for Employee entity.
 * <p>
 * This interface extends JpaRepository, which provides built-in CRUD operations
 * for the Employee entity without requiring explicit method implementation.
 * JpaRepository comes with default implementations for common database operations.
 *
 * The @RepositoryRestResource annotation allows customization of Spring Data REST endpoints.
 * In this case, the endpoint for the Employee entity will be available at the "/members" path.
 */
//@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // No need to write any code here or even an implementation class
    // JpaRepository provides methods like:
    // - findAll()
    // - findById(ID id)
    // - save(S entity)
    // - deleteById(ID id)
}
