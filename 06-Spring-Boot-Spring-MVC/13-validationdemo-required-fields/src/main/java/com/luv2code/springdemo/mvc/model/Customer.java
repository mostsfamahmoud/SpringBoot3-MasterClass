package com.luv2code.springdemo.mvc.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model class for representing a Customer.
 * <p>
 * This class includes fields for first and last names with validation
 * annotations to ensure data integrity during form submissions.
 */
public class Customer {

    // Field for the customer's first name (optional field)
    private String firstName;

    /**
     * Field representing the customer's last name.
     * <p>
     * - @NotNull ensures that the field cannot be null.
     * - @Size(min = 1) ensures that the field contains at least one character.
     * - A custom message ("is required") is provided for user-friendly error feedback
     * when validation fails.
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName = "";


    /**
     * Default constructor.
     * <p>
     * Initializes an empty Customer object. This is required for
     * frameworks like Spring to instantiate the model class without arguments.
     */
    public Customer() {
    }

    /**
     * Parameterized constructor.
     * <p>
     * Allows for creating a Customer object with initial values for
     * firstName and lastName fields.
     *
     * @param firstName The first name of the customer.
     * @param lastName  The last name of the customer.
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for the firstName field.
     *
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the firstName field.
     *
     * @param firstName The first name to set for the customer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the lastName field.
     *
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the lastName field.
     *
     * @param lastName The last name to set for the customer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Overrides the default `toString()` method to provide a custom
     * string representation of the Customer object.
     *
     * @return A string representing the customer's first and last names.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
