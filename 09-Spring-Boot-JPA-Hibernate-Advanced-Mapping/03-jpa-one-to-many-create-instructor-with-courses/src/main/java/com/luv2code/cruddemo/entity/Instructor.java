// Define the package for the entity class
package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Instructor entity.
 * This class maps to the "instructor" table in the database.
 * It includes a one-to-one relationship with the InstructorDetail entity.
 */
@Entity // Indicates that this class is a JPA entity mapped to a database table.
@Table(name = "instructor") // Maps this entity to the "instructor" table in the database.
public class Instructor {

    /**
     * Primary key for the "instructor" table.
     * The ID is auto-generated using the database's identity column.
     */
    @Id // Marks this field as the primary key for the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies that the ID value will be auto-generated by the database.
    @Column(name = "id") // Maps this field to the "id" column in the table.
    private int id;

    /**
     * Represents the first name of the instructor.
     * This maps to the "first_name" column in the database.
     */
    @Column(name = "first_name") // Maps this field to the "first_name" column in the table.
    private String firstName;

    /**
     * Represents the last name of the instructor.
     * This maps to the "last_name" column in the database.
     */
    @Column(name = "last_name") // Maps this field to the "last_name" column in the table.
    private String lastName;

    /**
     * Represents the email of the instructor.
     * This maps to the "email" column in the database.
     */
    @Column(name = "email") // Maps this field to the "email" column in the table.
    private String email;

    /**
     * Establishes a one-to-one relationship with the InstructorDetail entity.
     * The foreign key column "instructor_detail_id" links this entity to the InstructorDetail entity.
     * Cascade type is set to ALL, meaning all operations (persist, merge, remove, etc.) on this entity
     * will cascade to the associated InstructorDetail entity.
     */
    @OneToOne(cascade = CascadeType.ALL) // Defines a one-to-one relationship with cascading all operations.
    @JoinColumn(name = "instructor_detail_id") // Specifies the foreign key column in the "instructor" table.
    private InstructorDetail instructorDetail;


    @OneToMany(mappedBy = "instructor",
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            })
    private List<Course> courses;

    /**
     * Default no-argument constructor.
     * This is required by JPA for creating entity objects.
     */
    public Instructor() {
    }

    /**
     * Parameterized constructor to initialize an Instructor object with specific attributes.
     *
     * @param firstName The first name of the instructor.
     * @param lastName  The last name of the instructor.
     * @param email     The email address of the instructor.
     */
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter and Setter methods for each field

    /**
     * Gets the ID of the instructor.
     *
     * @return The unique identifier for the instructor.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the instructor.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the instructor.
     *
     * @return The first name of the instructor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the instructor.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the instructor.
     *
     * @return The last name of the instructor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the instructor.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the instructor.
     *
     * @return The email address of the instructor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the instructor.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the associated InstructorDetail object.
     *
     * @return The InstructorDetail entity linked to this instructor.
     */
    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    /**
     * Sets the associated InstructorDetail object.
     *
     * @param instructorDetail The InstructorDetail entity to link.
     */
    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Bi-directional relationship between course and instructor
    public void addCourse(Course newCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }

        // Adding course to the instructor's list of courses
        courses.add(newCourse);

        // Assigning the instructor to this new course
        newCourse.setInstructor(this);
    }

    /**
     * Provides a string representation of the Instructor object.
     * Includes all fields and the associated InstructorDetail object.
     *
     * @return A string representation of the instructor.
     */
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                ", courses=" + courses +
                '}';
    }
}
