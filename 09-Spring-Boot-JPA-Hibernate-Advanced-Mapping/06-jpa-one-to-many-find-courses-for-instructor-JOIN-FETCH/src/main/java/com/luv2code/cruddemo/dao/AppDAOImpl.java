// Define the package for the Data Access Object (DAO) implementation
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the AppDAO interface for managing CRUD operations
 * related to the Instructor and InstructorDetail entities.
 * <p>
 * This class uses JPA's EntityManager for database interactions
 * and is annotated as a Spring-managed component for dependency injection.
 */
@Repository
public class AppDAOImpl implements AppDAO {

    // Field for EntityManager to interact with the persistence context
    private final EntityManager entityManager;

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
     * Retrieves an InstructorDetail entity by its unique identifier (ID).
     *
     * @param id The unique identifier of the InstructorDetail to retrieve.
     * @return The InstructorDetail entity if found, or null if not found.
     */
    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        // Retrieve the InstructorDetail entity from the persistence context
        return entityManager.find(InstructorDetail.class, id);
    }


    /**
     * Retrieves a list of courses associated with a specific instructor.
     * <p>
     * This method uses a JPQL query to find all courses where the instructor's ID matches the given ID.
     *
     * @param id The ID of the instructor whose courses need to be retrieved.
     * @return A list of courses associated with the given instructor ID.
     */
    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // Create a TypedQuery to fetch courses for the given instructor ID
        TypedQuery<Course> typedQuery = entityManager.createQuery(
                "FROM Course WHERE instructor.id = :p_id", Course.class);

        // Set the parameter for the instructor ID
        typedQuery.setParameter("p_id", id);

        // Execute the query and return the result list
        return typedQuery.getResultList();
    }


    /**
     * Retrieves an instructor along with their associated courses using a JOIN FETCH query.
     * <p>
     * This method fetches the instructor and their courses in a single query to avoid lazy loading issues.
     *
     * @param id The ID of the instructor to be retrieved.
     * @return The Instructor object, including the list of associated courses.
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // Create a TypedQuery to fetch the instructor with his/her detail and their courses using JOIN FETCH
        TypedQuery<Instructor> typedQuery = entityManager.createQuery(
                "SELECT i FROM Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "WHERE i.id = :p_id", Instructor.class);

        // Set the parameter for the instructor ID
        typedQuery.setParameter("p_id", id);

        // Execute the query and return the single result
        return typedQuery.getSingleResult();
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

        if (tempInstructor != null) {
            // Remove the Instructor entity along with its associated details
            entityManager.remove(tempInstructor);
        }
    }

    /**
     * Deletes an InstructorDetail entity from the database by its unique identifier (ID).
     * <p>
     * Before deletion, the bidirectional link between the Instructor and
     * InstructorDetail is broken to maintain consistency.
     *
     * @param id The unique identifier of the InstructorDetail to delete.
     */
    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // Retrieve the InstructorDetail entity from the persistence context
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        if (instructorDetail != null) {
            // Remove the link between the Instructor and InstructorDetail
            instructorDetail.getInstructor().setInstructorDetail(null);

            // Remove the InstructorDetail entity
            entityManager.remove(instructorDetail);
        }
    }
}
