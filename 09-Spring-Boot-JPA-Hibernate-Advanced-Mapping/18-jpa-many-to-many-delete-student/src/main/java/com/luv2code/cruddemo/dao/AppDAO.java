// Define the package for the Data Access Object (DAO) interface
package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import java.util.List;

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
     * Saves a Course entity to the database.
     *
     * @param course The Course entity to be saved.
     */
    void save(Course course);


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
     * Retrieves an InstructorDetail entity by its unique identifier (ID).
     *
     * @param id The unique identifier of the InstructorDetail to retrieve.
     * @return The InstructorDetail entity if found, or null if not found.
     */
    InstructorDetail findInstructorDetailById(int id);


    /**
     * Retrieves a list of courses associated with a specific instructor.
     * <p>
     * This method uses a JPQL query to find all courses where the instructor's ID matches the given ID.
     *
     * @param id The ID of the instructor whose courses need to be retrieved.
     * @return A list of courses associated with the given instructor ID.
     */
    List<Course> findCoursesByInstructorId(int id);

    /**
     * Retrieves an instructor along with their associated courses using a JOIN FETCH query.
     * <p>
     * This method fetches the instructor and their courses in a single query to avoid lazy loading issues.
     *
     * @param id The ID of the instructor to be retrieved.
     * @return The Instructor object, including the list of associated courses.
     */
    Instructor findInstructorByIdJoinFetch(int id);


    /**
     * Finds a course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return The Course object if found; otherwise, returns null.
     */
    Course findCourseById(int id);

    /**
     * Retrieves a Course entity along with its associated reviews by the course ID.
     * <p>
     * This method uses a JPQL (Java Persistence Query Language) query with JOIN FETCH to
     * fetch the Course entity and its reviews in a single query, ensuring the associated
     * reviews are loaded eagerly and avoiding potential lazy loading issues.
     *
     * @param id The unique identifier (ID) of the Course to retrieve.
     * @return The Course entity along with its associated reviews if found; otherwise, null.
     */
    Course findCourseAndReviewsByCourseId(int id);

    /**
     * Retrieves a Course entity along with its associated students by the course ID.
     * <p>
     * This method uses a JPQL query with JOIN FETCH to fetch the Course entity and its students
     * in a single query, ensuring the associated students are loaded eagerly.
     *
     * @param id The unique identifier (ID) of the Course to retrieve.
     * @return The Course entity along with its associated students if found; otherwise, null.
     */
    Course findCourseAndStudentsByCourseId(int id);

    /**
     * Retrieves a Student entity along with its associated courses by the student ID.
     * <p>
     * This method uses a JPQL query with JOIN FETCH to fetch the Student entity and its courses
     * in a single query, ensuring the associated courses are loaded eagerly.
     *
     * @param id The unique identifier (ID) of the Student to retrieve.
     * @return The Student entity along with its associated courses if found; otherwise, null.
     */
    Student findStudentAndCoursesByStudentId(int id);


    /**
     * Updates the information of an existing instructor.
     * <p>
     * This method merges the provided instructor entity with the existing one in the database.
     * If the instructor does not exist, it will be created.
     *
     * @param updatedInstructor The Instructor object containing updated information.
     */
    void update(Instructor updatedInstructor);

    /**
     * Updates the information of an existing course.
     * <p>
     * This method merges the provided course entity with the existing one in the database.
     * If the course does not exist, it will be created.
     *
     * @param updatedCourse The Course object containing updated information.
     */
    void update(Course updatedCourse);

    /**
     * Updates the information of an existing student.
     * <p>
     * This method merges the provided student entity with the existing one in the database.
     * If the student does not exist, it will be created.
     *
     * @param updatedStudent The Student object containing updated information.
     */
    void update(Student updatedStudent);


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

    /**
     * Deletes an InstructorDetail entity from the database by its unique identifier (ID).
     * <p>
     * Before deletion, the bidirectional link between the Instructor and
     * InstructorDetail is broken to maintain consistency.
     *
     * @param id The unique identifier of the InstructorDetail to delete.
     */
    void deleteInstructorDetailById(int id);

    /**
     * Deletes a course from the database by its ID.
     *
     * @param id The ID of the course to delete.
     */
    void deleteCourseById(int id);

    /**
     * Deletes a student from the database by its ID.
     *
     * @param id The ID of the student to delete.
     */
    void deleteStudentById(int id);
}
