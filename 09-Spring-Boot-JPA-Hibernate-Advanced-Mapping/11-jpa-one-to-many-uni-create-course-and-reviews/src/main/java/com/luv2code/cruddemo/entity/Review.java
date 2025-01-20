package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

/**
 * Represents a Review entity, mapped to the 'review' table in the database.
 * Each review contains a unique ID and a comment.
 */
@Entity
@Table(name = "review")
public class Review {

    // Primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Comment associated with the review
    @Column(name = "comment")
    private String comment;

    /**
     * Default no-argument constructor.
     * Required by JPA for entity instantiation.
     */
    public Review() {
    }

    /**
     * Constructor to initialize the review with a comment.
     *
     * @param comment The text of the review comment.
     */
    public Review(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the unique ID of the review.
     *
     * @return The ID of the review.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the review.
     *
     * @param id The ID to be assigned to the review.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the comment of the review.
     *
     * @return The review comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the review.
     *
     * @param comment The text of the comment to be set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns a string representation of the review entity.
     *
     * @return A string containing the ID and comment of the review.
     */
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
