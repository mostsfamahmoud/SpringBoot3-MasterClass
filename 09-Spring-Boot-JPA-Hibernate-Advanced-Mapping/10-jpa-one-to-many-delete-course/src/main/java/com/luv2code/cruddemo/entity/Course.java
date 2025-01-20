package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

/**
 * Represents a Course entity, mapped to the 'course' table in the database.
 * Each course is associated with an instructor through a Many-to-One relationship.
 */
@Entity
@Table(name = "course")
public class Course {

    // Primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Title of the course
    @Column(name = "title")
    private String title;

    // Many-to-One association with the Instructor entity
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // Default constructor
    public Course() {
    }

    // Constructor to initialize the course with a title
    public Course(String title) {
        this.title = title;
    }

    // Getter and setter for the course ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for the course title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for the associated instructor
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // Provides a string representation of the course object
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
