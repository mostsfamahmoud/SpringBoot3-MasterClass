// Define the package for the Data Access Object (DAO) implementation
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the AppDAO interface for managing CRUD operations
 * related to the Instructor entity.
 * <p>
 * This class uses JPA's EntityManager for database interactions.
 * It is annotated as a Spring-managed component for dependency injection.
 */
@Repository
public class AppDAOImpl implements AppDAO {

    // Field for EntityManager to interact with the persistence context
    private EntityManager entityManager;

    /**
     * Constructor-based dependency injection for EntityManager.
     *
     * @param entityManager The JPA EntityManager to manage persistence operations.
     */
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Saves an Instructor entity to the database.
     * <p>
     * The associated InstructorDetail entity is also persisted due to
     * the cascading behavior defined with CascadeType.ALL.
     *
     * @param instructor The Instructor entity to be saved.
     */
    @Override
    @Transactional
    public void save(Instructor instructor) {
        // Persist the Instructor entity along with its associated InstructorDetail
        entityManager.persist(instructor);
    }

    /**
     * Retrieves an Instructor entity by its unique identifier (ID).
     * <p>
     * The associated InstructorDetail entity is also retrieved due to the
     * default FetchType.EAGER configuration in the @OneToOne relationship.
     *
     * @param id The unique identifier of the Instructor to retrieve.
     * @return The Instructor entity if found, or null if not found.
     */
    @Override
    public Instructor findInstructorById(int id) {
        // Retrieve the Instructor entity from the persistence context
        return entityManager.find(Instructor.class, id);
    }

    /**
     * Deletes an Instructor entity from the database by its unique identifier (ID).
     * <p>
     * The associated InstructorDetail entity is also deleted due to
     * the cascading behavior defined with CascadeType.ALL.
     *
     * @param id The unique identifier of the Instructor to delete.
     */
    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // Retrieve the Instructor entity from the persistence context
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        // Remove the Instructor entity along with its associated details
        entityManager.remove(tempInstructor);
    }
}
