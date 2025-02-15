package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Student class is a JPA entity that maps to the "student" table in the database.
 * It represents a student record with fields such as ID, first name, last name, and email.
 * Additionally, it maintains a Many-to-Many relationship with the Course entity,
 * allowing a student to enroll in multiple courses.
 */
@Entity // Marks this class as a JPA entity to be managed by the JPA provider
@Table(name = "student") // Specifies the table name in the database
public class Student {

    // Define fields

    /**
     * The unique identifier for the student.
     * Auto-incremented by the database.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies that the ID value will be generated by the database (auto-increment)
    @Column(name = "id") // Maps this field to the "id" column in the database
    private int id;

    /**
     * The first name of the student.
     */
    @Column(name = "first_name") // Maps this field to the "first_name" column in the database
    private String firstName;

    /**
     * The last name of the student.
     */
    @Column(name = "last_name") // Maps this field to the "last_name" column in the database
    private String lastName;

    /**
     * The email address of the student.
     */
    @Column(name = "email") // Maps this field to the "email" column in the database
    private String email;

    /**
     * A list of courses the student is enrolled in.
     * Defined as a Many-to-Many relationship because a student can enroll in multiple courses,
     * and a course can have multiple students.
     * The relationship is lazily loaded to optimize performance.
     * Cascade types ensure that certain operations on the student propagate to the courses.
     * The @JoinTable annotation defines the join table 'course_student' to manage the relationship.
     */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "course_student", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id"), // Foreign key for the student
            inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for the course
    )
    private List<Course> courses;

    // Define constructors

    /**
     * Default constructor for JPA.
     * It is required by the JPA specification for entity instantiation.
     */
    public Student() {
        // No-arg constructor
    }

    /**
     * Parameterized constructor to create a new Student instance.
     *
     * @param firstName The first name of the student.
     * @param lastName  The last name of the student.
     * @param email     The email of the student.
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Define setters/getters

    /**
     * Gets the unique ID of the student.
     *
     * @return The ID of the student.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the student.
     *
     * @param id The ID to be assigned to the student.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the student.
     *
     * @return The first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName The first name to be assigned to the student.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the student.
     *
     * @return The last name of the student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName The last name to be assigned to the student.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the student.
     *
     * @return The email address of the student.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     *
     * @param email The email address to be assigned to the student.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of courses the student is enrolled in.
     *
     * @return The list of courses.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses the student is enrolled in.
     *
     * @param courses The list of courses to be assigned to the student.
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Adds a new course to the student's list of enrolled courses.
     * If the list of courses is null, it initializes the list.
     *
     * @param newCourse The course to be added to the student.
     */
    public void addCourse(Course newCourse) {
        if (courses == null) {
            courses = new ArrayList<>(); // Initialize the list if it doesn't exist
        }
        courses.add(newCourse);
    }

    // Define toString() method

    /**
     * Generates a string representation of the Student object.
     *
     * @return A string with the student's ID, first name, last name, and email.
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}