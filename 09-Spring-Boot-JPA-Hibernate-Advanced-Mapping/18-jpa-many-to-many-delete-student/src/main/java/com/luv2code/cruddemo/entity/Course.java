package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Course entity, mapped to the 'course' table in the database.
 * Each course is associated with an instructor (Many-to-One relationship),
 * may have multiple reviews (One-to-Many relationship),
 * and can be enrolled by multiple students (Many-to-Many relationship).
 */
@Entity
@Table(name = "course")
public class Course {

    /**
     * The unique identifier for the course.
     * Auto-incremented by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * The title of the course.
     */
    @Column(name = "title")
    private String title;

    /**
     * The instructor associated with this course.
     * Defined as a Many-to-One relationship because a single instructor can teach multiple courses.
     * The cascade types ensure that certain operations on the course will propagate to the instructor entity.
     */
    @ManyToOne(cascade = {
            CascadeType.DETACH,  // Detach the entity from the persistence context
            CascadeType.MERGE,  // Merge updates to the entity
            CascadeType.PERSIST,  // Persist new entities
            CascadeType.REFRESH  // Refresh the entity state from the database
    })
    @JoinColumn(name = "instructor_id") // Foreign key in the 'course' table
    private Instructor instructor;

    /**
     * A list of reviews associated with the course.
     * Defined as a One-to-Many relationship because a course can have multiple reviews.
     * Reviews are lazily loaded to optimize performance and reduce unnecessary data loading.
     * CascadeType.ALL ensures that operations on the course propagate to its reviews.
     * The @JoinColumn annotation maps this relationship to the "course_id" column in the 'review' table.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id") // Foreign key in the 'review' table
    private List<Review> reviews;

    /**
     * A list of students enrolled in the course.
     * Defined as a Many-to-Many relationship because a course can have multiple students,
     * and a student can enroll in multiple courses.
     * The relationship is lazily loaded to optimize performance.
     * Cascade types ensure that certain operations on the course propagate to the students (Except Removal).
     * The @JoinTable annotation defines the join table 'course_student' to manage the relationship.
     */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "course_student", // Name of the join table
            joinColumns = @JoinColumn(name = "course_id"), // Foreign key for the course
            inverseJoinColumns = @JoinColumn(name = "student_id") // Foreign key for the student
    )
    private List<Student> students;

    /**
     * Default no-argument constructor.
     * Required by JPA for entity instantiation.
     */
    public Course() {
    }

    /**
     * Constructs a Course with a specified title.
     *
     * @param title The title of the course.
     */
    public Course(String title) {
        this.title = title;
    }

    /**
     * Gets the unique ID of the course.
     *
     * @return The ID of the course.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the course.
     *
     * @param id The ID to be assigned to the course.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the course.
     *
     * @return The course title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title The title to be assigned to the course.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the instructor associated with the course.
     *
     * @return The instructor teaching the course.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor associated with the course.
     *
     * @param instructor The instructor to be assigned to the course.
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Gets the list of reviews for the course.
     *
     * @return The list of reviews.
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets the list of reviews for the course.
     *
     * @param reviews The list of reviews to be assigned to the course.
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Adds a new review to the course.
     * If the list of reviews is null, it initializes the list.
     *
     * @param newReview The review to be added to the course.
     */
    public void addReview(Review newReview) {
        if (reviews == null) {
            reviews = new ArrayList<>(); // Initialize the list if it doesn't exist
        }
        reviews.add(newReview);
    }

    /**
     * Gets the list of students enrolled in the course.
     *
     * @return The list of students.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students enrolled in the course.
     *
     * @param students The list of students to be assigned to the course.
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Adds a new student to the course.
     * If the list of students is null, it initializes the list.
     *
     * @param newStudent The student to be added to the course.
     */
    public void addStudent(Student newStudent) {
        if (students == null) {
            students = new ArrayList<>(); // Initialize the list if it doesn't exist
        }
        students.add(newStudent);
    }

    /**
     * Returns a string representation of the course.
     * Includes only the ID and title for brevity.
     *
     * @return A string representation of the course.
     */
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}