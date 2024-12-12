// Define the package for the DAO implementation
package com.luv2Code.springboot.cruddemo.dao;

// Import necessary classes and annotations

import com.luv2Code.springboot.cruddemo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the UserDAO interface using JPA.
 * <p>
 * This class provides database access methods for the User entity
 * by leveraging JPA's EntityManager. The @Repository annotation
 * designates this class as a Spring-managed DAO component.
 */
@Repository
public class UserDaoImpl implements UserDao {

    // The EntityManager used for interacting with the database
    private EntityManager entityManager;

    /**
     * Constructor-based dependency injection for EntityManager.
     * <p>
     * The @Autowired annotation ensures that an instance of EntityManager
     * is automatically injected by Spring at runtime.
     *
     * @param entityManager The EntityManager instance provided by Spring.
     */
    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds a user by their username.
     * <p>
     * This method queries the database to retrieve a User entity
     * with the specified username and ensures the user is enabled.
     * If no matching user is found, or if an exception occurs,
     * the method returns null.
     *
     * @param userName The username to search for in the database.
     * @return A User object if a match is found; otherwise, null.
     */
    @Override
    public User findByUserName(String userName) {
        /**
         * Create a JPQL query to fetch the user with the given username and ensure the user is enable.
         *
         * JPQL Note:
         * The query uses JPQL (Java Persistence Query Language), which operates on entity fields
         * and class names, not on database column names or table names. Here, "User" refers to
         * the User entity class, and "userName" and "enabled" refer to its fields.
         * This makes JPQL queries database-independent and aligns with the JPA entity model.
         */
        TypedQuery<User> userTypedQuery = entityManager.createQuery(
                "FROM User " +
                        "WHERE userName = :pUserName AND enabled = true", // JPQL query using entity fields
                User.class // Specify the type of the result as User
        );

        // Set the username parameter for the query
        userTypedQuery.setParameter("pUserName", userName);

        // Initialize the user object to null
        User user = null;

        try {
            // Execute the query and fetch a single result
            user = userTypedQuery.getSingleResult();
        } catch (Exception e) {
            // Handle cases where no user is found [NO_DATA_FOUND] or other exceptions occur
            user = null;
        }

        // Return the result (either a User object or null)
        return user;
    }
}
