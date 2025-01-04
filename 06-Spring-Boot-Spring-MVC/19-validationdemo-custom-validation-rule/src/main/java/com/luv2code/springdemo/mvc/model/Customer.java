package com.luv2code.springdemo.mvc.model;

import com.luv2code.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

/**
 * Represents a Customer in the system.
 * <p>
 * This model class includes fields for the customer's first name, last name, free passes, postal code, and course code.
 * It uses validation annotations to ensure data integrity during user input and form submissions.
 * Provides getter and setter methods for each field to facilitate data binding and access.
 * </p>
 */
public class Customer {

    /**
     * Field for the customer's first name (optional field).
     * No validation constraints are applied, making this field optional.
     */
    private String firstName;

    /**
     * Field for the customer's last name.
     * <p>
     * Validation constraints:
     * - {@code @NotNull}: Ensures the field is not null.
     * - {@code @Size(min = 1)}: Ensures the field contains at least one character.
     * <p>
     * A custom error message ("is required") is specified to provide meaningful feedback
     * when validation fails.
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName = "";

    /**
     * Field for the customer's number of free passes.
     * <p>
     * Validation constraints:
     * - {@code @NotNull}: Ensures the field is not null.
     * - {@code @Min(0)}: Ensures the value is at least 0.
     * - {@code @Max(10)}: Ensures the value is no more than 10.
     * </p>
     * Custom Error Handling:
     * - {@code typeMismatch.customer.freePasses}: Defined in `messages.properties` as
     * "Invalid number. Please enter a valid integer between 0 and 10."
     * This message is displayed when a type mismatch occurs during data binding.
     */
    @NotNull(message = "is required")
    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to 10")
    private Integer freePasses;

    /**
     * Field for the customer's postal code.
     * <p>
     * Validation constraint:
     * - {@code @Pattern(regexp = "^[a-zA-Z0-9]{5}")}: Ensures the postal code contains exactly 5 alphanumeric characters (letters or digits).
     * </p>
     * A custom error message ("Only 5 chars/digits") is specified to notify the user when the input does not match the expected format.
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 chars/digits")
    private String postalCode;

    /**
     * Field for the customer's course code.
     * <p>
     * Validation constraint:
     * - {@code @CourseCode}: Custom validation annotation ensures that the input starts with the specified prefix (default: "CSE").
     * </p>
     * A custom error message ("Must start with CSE") is specified in case the validation fails.
     */
    @CourseCode(value = "CSE", message = "Must start with CSE")
    private String courseCode;

    /**
     * Default constructor.
     * <p>
     * Initializes an empty {@code Customer} object. This constructor is required by frameworks like Spring
     * to instantiate the model during data binding.
     * </p>
     */
    public Customer() {
    }

    /**
     * Parameterized constructor for initializing all fields.
     *
     * @param firstName  The first name of the customer.
     * @param lastName   The last name of the customer.
     * @param freePasses The number of free passes for the customer.
     * @param postalCode The postal code of the customer.
     * @param courseCode The course code of the customer.
     */
    public Customer(String firstName, String lastName, Integer freePasses, String postalCode, String courseCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.freePasses = freePasses;
        this.postalCode = postalCode;
        this.courseCode = courseCode;
    }

    /**
     * Getter for the {@code firstName} field.
     *
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the {@code firstName} field.
     *
     * @param firstName The first name to set for the customer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the {@code lastName} field.
     *
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the {@code lastName} field.
     *
     * @param lastName The last name to set for the customer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the {@code freePasses} field.
     *
     * @return The number of free passes for the customer.
     */
    public Integer getFreePasses() {
        return freePasses;
    }

    /**
     * Setter for the {@code freePasses} field.
     *
     * @param freePasses The number of free passes to set for the customer.
     */
    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    /**
     * Getter for the {@code postalCode} field.
     *
     * @return The postal code of the customer.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for the {@code postalCode} field.
     *
     * @param postalCode The postal code to set for the customer.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for the {@code courseCode} field.
     *
     * @return The course code of the customer.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Setter for the {@code courseCode} field.
     *
     * @param courseCode The course code to set for the customer.
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Overrides the {@code toString} method to provide a string representation of the {@code Customer} object.
     *
     * @return A string representation of the {@code Customer} object, including all field values.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", freePasses=" + freePasses +
                ", postalCode='" + postalCode + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
