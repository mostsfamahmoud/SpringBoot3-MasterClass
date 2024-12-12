// Define the package for the error response class
package com.luv2Code.demo.rest;

/** Step 1:
 * A custom error response class used to encapsulate error details
 * for RESTful responses in the application.
 *
 * This class is designed to standardize the structure of error
 * messages sent back to clients when exceptions are thrown.
 */
public class StudentErrorResponse {

    // HTTP status code of the error
    private int status;

    // Detailed error message
    private String message;

    // Timestamp indicating when the error occurred
    private long timeStamp;

    /**
     * Default constructor.
     *
     * Initializes the object without setting any fields. This is useful for cases
     * where fields will be set individually using setter methods.
     */
    public StudentErrorResponse() {
    }

    /**
     * Parameterized constructor.
     *
     * Allows initialization of all fields during object creation.
     *
     * @param message   The error message.
     * @param timeStamp The timestamp of when the error occurred.
     * @param status    The HTTP status code associated with the error.
     */
    public StudentErrorResponse(String message, long timeStamp, int status) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    /**
     * Getter for the HTTP status code.
     *
     * @return The HTTP status code as an integer.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter for the HTTP status code.
     *
     * @param status The HTTP status code to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Getter for the error message.
     *
     * @return The error message as a String.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for the error message.
     *
     * @param message The error message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter for the timestamp of the error.
     *
     * @return The timestamp as a long value.
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Setter for the timestamp of the error.
     *
     * @param timeStamp The timestamp to set as a long value.
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
