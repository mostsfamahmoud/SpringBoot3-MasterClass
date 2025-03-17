// Define the package for the DAO class
package com.luv2Code.demomvcsecurity.dao;

// Import necessary classes for managing entities and dependency injection
import com.luv2Code.demomvcsecurity.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the RoleDao interface for interacting with Role entities.
 * <p>
 * This class provides methods to interact with the Role data in the database
 * using JPA EntityManager.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    // JPA EntityManager to manage persistence and queries
    private EntityManager entityManager;

    /**
     * Constructor for dependency injection of EntityManager.
     *
     * @param entityManager The EntityManager instance to use.
     */
    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds a Role entity by its name.
     *
     * @param roleName The name of the role to find.
     * @return The Role entity if found, or null otherwise.
     */
    @Override
    public Role findRoleByName(String roleName) {

        // Define a JPQL (Java Persistence Query Language) query to fetch the Role entity
        // based on its name
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery(
                "FROM Role " + // Fetch Role entities
                        "WHERE name = :rName", // Filter based on the "name" field
                Role.class);

        // Set the query parameter "rName" to the provided roleName
        roleTypedQuery.setParameter("rName", roleName);

        // Initialize the role object to null
        Role role = null;

        try {
            // Execute the query and fetch a single result
            role = roleTypedQuery.getSingleResult();
        } catch (Exception e) {
            // Handle cases where no role is found or other exceptions occur
            // Example: NoResultException for no matching result
        }

        // Return the result (either a Role object or null)
        return role;
    }
}
