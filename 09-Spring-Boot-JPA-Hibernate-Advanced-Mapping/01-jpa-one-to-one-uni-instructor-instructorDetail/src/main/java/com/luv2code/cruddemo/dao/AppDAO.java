// Define the package for the Data Access Object (DAO) interface
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;

/**
 * Data Access Object (DAO) interface for handling CRUD operations
 * related to the Instructor entity.
 * <p>
 * Provides an abstraction layer for database access, defining methods
 * for saving, retrieving, and deleting Instructor entities.
 */
public interface AppDAO {

    /**
     * Saves an Instructor entity to the database.
     * <p>
     * If the Instructor has an associated InstructorDetail object,
     * the cascade settings will propagate changes to the related entity.
     *
     * @param instructor The Instructor entity to save. Should not be null.
     */
    void save(Instructor instructor);

    /**
     * Finds an Instructor entity by its unique identifier (ID).
     * <p>
     * Retrieves the Instructor from the database. If the Instructor has an
     * associated InstructorDetail object, it will be included due to the relationship mapping.
     *
     * @param id The unique identifier of the Instructor to retrieve.
     *           Must be a valid and existing ID.
     * @return The Instructor entity if found, or null if no such entity exists.
     */
    Instructor findInstructorById(int id);

    /**
     * Deletes an Instructor entity from the database by its unique identifier (ID).
     * <p>
     * If the cascade settings are applied, the associated InstructorDetail entity
     * will also be deleted.
     *
     * @param id The unique identifier of the Instructor to delete.
     *           Must be a valid and existing ID.
     */
    void deleteInstructorById(int id);
}
