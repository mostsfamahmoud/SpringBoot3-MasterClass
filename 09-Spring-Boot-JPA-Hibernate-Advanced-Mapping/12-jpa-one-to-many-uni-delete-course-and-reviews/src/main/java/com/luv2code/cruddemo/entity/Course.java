package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Course entity, mapped to the 'course' table in the database.
 * Each course is associated with an instructor (Many-to-One relationship)
 * and may have multiple reviews (One-to-Many relationship).
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
