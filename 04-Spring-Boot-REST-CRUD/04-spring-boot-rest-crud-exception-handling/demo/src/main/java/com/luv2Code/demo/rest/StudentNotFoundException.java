// Define the package for the custom exception class
package com.luv2Code.demo.rest;

/** Step 2:
 * A custom exception class used for handling cases where a student cannot be found.
 *
 * This class extends the {@link RuntimeException}, making it an unchecked exception.
 * It is designed to provide detailed error messages and optional causes
 * when an exception related to student retrieval occurs.
 */
public class StudentNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a message string.
     *
     * This constructor is used when there is a specific error message to describe
     * the reason for the exception.
     *
     * @param message The error message describing why the exception occurred.
     */
    public StudentNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor that accepts a message string and a throwable cause.
     *
     * This constructor is used when there is a specific error message and an underlying
     * cause for the exception (e.g., another exception that led to this one).
     *
     * @param message The error message describing why the exception occurred.
     * @param cause   The underlying cause of the exception.
     */
    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor that accepts only a throwable cause.
     *
     * This constructor is used when the exception is caused by another throwable
     * and no specific error message is needed.
     *
     * @param cause The underlying cause of the exception.
     */
    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
