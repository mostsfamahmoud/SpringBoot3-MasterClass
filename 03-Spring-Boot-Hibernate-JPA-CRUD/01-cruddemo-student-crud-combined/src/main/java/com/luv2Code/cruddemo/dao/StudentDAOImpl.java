// Define the package for this class
package com.luv2Code.cruddemo.dao;

// Import necessary classes and annotations
import com.luv2Code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StudentDAOImpl is the implementation of the StudentDAO interface.
 * This class interacts with the database using the EntityManager for
 * CRUD operations on the Student entity.
 *
 * The @Repository annotation indicates that this class is a Spring
 * repository that provides CRUD operations and exception translation.
 *
 * Supports Component Scanning and Translates JDBC Exceptions into unchecked Exceptions.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {

    // Define a field for the EntityManager
    private EntityManager entityManager;

    /**
     * Constructor for injecting the EntityManager using constructor injection.
     *
     * @param entityManager The EntityManager to interact with the persistence context.
     */
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Saves a Student object to the database.
     * The @Transactional annotation ensures that the method runs within a transaction.
     *
     * @param student The Student object to be saved.
     */
    @Override
    @Transactional // Handles transaction management for saving/updating entities.
    public void save(Student student) {
        entityManager.persist(student); // Persist the student entity to the database.
    }

    /**
     * Finds a Student in the database by its ID.
     *
     * @param id The ID of the student to find.
     * @return The Student object if found; otherwise, returns null.
     * This method retrieves a student record from the database based on the provided ID.
     */
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); // Returns the student entity if found.
    }

    /**
     * Retrieves all Student records from the database.
     *
     * @return A list of all Student objects.
     * This method fetches all student records and returns them as a list.
     */
    @Override
    public List<Student> findAll() {
        /**
         * Create a query to fetch all Student entities, ordered by last name (optional).
         * The query uses JPQL to select all records from the Student entity and orders them in descending order based on the last name.
         *
         * Here --> Student: Name of the JPA Entity (The Java Class)
         *          lastName: Name of the Field of the JPA Entity (Within the Java Class)
         */
        TypedQuery<Student> theQuerySorted = entityManager.createQuery(
                "FROM Student " +
                        "ORDER BY lastName DESC",
                Student.class
        );

        // Create a query to fetch all Student entities
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // Return the results of the query as a list.
        return theQuery.getResultList();
    }

    /**
     * Finds a list of Student objects with a specific last name.
     *
     * @param theLastName The last name to search for.
     * @return A list of Student objects matching the last name.
     * This method retrieves student records that have the specified last name.
     */
    @Override
    public List<Student> findByLastName(String theLastName) {
        // Create a query to find students by last name.
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName = :theData", Student.class);

        // Set the query parameter for the last name.
        theQuery.setParameter("theData", theLastName);

        // Return the results of the query as a list.
        return theQuery.getResultList();
    }

    /**
     * Updates an existing Student object in the database.
     *
     * @param theStudent The Student object containing updated information.
     * This method modifies the corresponding record in the database.
     */
    @Override
    @Transactional // Marks this method as transactional to manage database operations automatically.
    public void update(Student theStudent) {
        // The merge() method updates an existing Student entity in the database.
        // If the entity does not exist in the persistence context, it will be added as a new record.
        entityManager.merge(theStudent); // Updates or adds the student entity.
    }

    /**
     * Deletes a Student from the database by its ID.
     *
     * @param Id The ID of the student to be deleted.
     * This method removes the specified student record from the database.
     */
    @Override
    @Transactional
    public void delete(Integer Id) {
        // Get the student object that will be removed
        Student theStudent = entityManager.find(Student.class, Id); // Find the student by ID.

        // Delete the student with the specified ID
        entityManager.remove(theStudent); // Remove the student entity from the database.
    }

    /**
     * Deletes all Student records from the database.
     *
     * @return The number of rows deleted.
     * This method clears all student records from the database and returns the count of deleted records.
     */
    @Override
    @Transactional
    public int deleteAll() {
        // Execute delete query.
        return entityManager.createQuery("DELETE FROM Student").executeUpdate(); // Return the number of deleted rows.
    }
}
