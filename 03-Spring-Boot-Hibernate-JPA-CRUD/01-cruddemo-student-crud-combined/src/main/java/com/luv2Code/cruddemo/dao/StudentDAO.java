// Define the package for this interface
package com.luv2Code.cruddemo.dao;

// Import the Student entity class
import com.luv2Code.cruddemo.entity.Student;

// Import the List collection framework
import java.util.List;

/**
 * StudentDAO is a Data Access Object (DAO) interface that defines
 * the methods for interacting with Student data in the database.
 * This interface provides an abstraction layer to perform CRUD operations
 * on Student entities.
 */
public interface StudentDAO {

    /**
     * Saves a Student object to the database.
     *
     * @param student The Student object to be saved.
     * This method will persist the student data in the database.
     */
    void save(Student student);

    /**
     * Finds a Student in the database by its ID.
     *
     * @param id The ID of the student to find.
     * @return The Student object if found, otherwise returns null.
     * This method retrieves a specific student record based on the given ID.
     */
    Student findById(Integer id);

    /**
     * Retrieves all Student records from the database.
     *
     * @return A list of all Student objects.
     * This method returns all student records as a list.
     */
    List<Student> findAll();

    /**
     * Finds a list of Student objects with a specific last name.
     *
     * @param theLastName The last name to search for.
     * @return A list of Student objects matching the last name.
     * This method retrieves students whose last name matches the provided parameter.
     */
    List<Student> findByLastName(String theLastName);

    /**
     * Updates an existing Student object in the database.
     *
     * @param student The Student object containing updated information.
     * This method modifies the corresponding record in the database.
     */
    void update(Student student);

    /**
     * Deletes a Student from the database by its ID.
     *
     * @param Id The ID of the student to be deleted.
     * This method removes the specified student record from the database.
     */
    void delete(Integer Id);

    /**
     * Deletes all Student records from the database.
     *
     * @return The number of rows deleted.
     * This method clears all student records from the database and returns the count of deleted records.
     */
    int deleteAll();
}
