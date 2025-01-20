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
@Entity
@Table(name = "instructor") // Maps this entity to the "instructor" table in the database.
public class Instructor {

    /**
     * Primary key for the "instructor" table.
     * The ID is auto-generated using the database's identity column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generation using the database.
    @Column(name = "id") // Maps this field to the "id" column in the table.
    private int id;

    /**
     * Represents the first name of the instructor.
     */
    @Column(name = "first_name") // Maps this field to the "first_name" column in the table.
    private String firstName;

    /**
     * Represents the last name of the instructor.
     */
    @Column(name = "last_name") // Maps this field to the "last_name" column in the table.
    private String lastName;

    /**
     * Represents the email of the instructor.
     */
    @Column(name = "email") // Maps this field to the "email" column in the table.
    private String email;

    /**
     * One-to-one relationship with the InstructorDetail entity.
     * Cascade type is set to ALL, meaning all operations (persist, merge, remove, etc.)
     * on this entity will cascade to the associated InstructorDetail entity.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id") // Foreign key in the "instructor" table.
    private InstructorDetail instructorDetail;

    /**
     * One-to-many relationship with the Course entity.
     * The "mappedBy" attribute indicates that the "instructor" field in the Course entity
     * owns the relationship.
     * The cascade operations include DETACH, MERGE, PERSIST, and REFRESH.
     * By default -> FetchType: LAZY FETCH
     */
    @OneToMany(
            mappedBy = "instructor",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<Course> courses;

    /**
     * Default no-argument constructor.
     * Required by JPA for creating entity objects.
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

    // Getter and Setter methods for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Adds a new course to the instructor's list of courses.
     * Establishes the bi-directional relationship between the course and instructor.
     *
     * @param newCourse The course to add.
     */
    public void addCourse(Course newCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(newCourse);
        newCourse.setInstructor(this); // Set this instructor for the new course.
    }

    /**
     * Provides a string representation of the Instructor object.
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
                '}';
    }
}
