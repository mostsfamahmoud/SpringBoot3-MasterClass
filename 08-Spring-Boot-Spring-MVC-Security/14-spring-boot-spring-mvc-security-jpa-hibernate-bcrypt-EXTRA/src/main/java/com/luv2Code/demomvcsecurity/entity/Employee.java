// Define the package for the entity class
package com.luv2Code.demomvcsecurity.entity;

import jakarta.persistence.*;

/**
 * The Employee class represents an entity corresponding to the "employee" table in the database.
 *
 * This class is annotated with JPA annotations to define its mapping to the database table.
 */
@Entity
@Table(name="employee")
public class Employee {

    // Define fields

    /**
     * The unique identifier for an employee.
     *
     * Annotated with @Id to indicate it is the primary key.
     * Annotated with @GeneratedValue to specify the primary key generation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Maps the field to the "id" column in the database
    private int id;

    /**
     * The first name of the employee.
     *
     * Annotated with @Column to map to the "first_name" column in the database.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the employee.
     *
     * Annotated with @Column to map to the "last_name" column in the database.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The email of the employee.
     *
     * Annotated with @Column to map to the "email" column in the database.
     */
    @Column(name = "email")
    private String email;

    // Constructors

    /**
     * Default no-arg constructor required by JPA.
     *
     * This constructor is necessary for JPA to create instances of this entity class.
     */
    public Employee() {
    }

    /**
     * Parameterized constructor for creating Employee objects.
     *
     * @param firstName The first name of the employee.
     * @param lastName  The last name of the employee.
     * @param email     The email of the employee.
     */
    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters

    /**
     * Gets the unique ID of the employee.
     *
     * @return The ID of the employee.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the employee.
     *
     * @param id The ID to set for the employee.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the employee.
     *
     * @return The first name of the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName The first name to set for the employee.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     *
     * @return The last name of the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName The last name to set for the employee.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the employee.
     *
     * @return The email of the employee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the employee.
     *
     * @param email The email to set for the employee.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // Override toString

    /**
     * Returns a string representation of the Employee object.
     *
     * @return A string with the employee's details, including ID, first name, last name, and email.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
