package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating course codes.
 * <p>
 * This annotation is designed to ensure that a string field (such as a course code)
 * starts with a specific prefix, defined by the user or defaulted to "LUV".
 * <p>
 * Usage:
 * - Apply this annotation to a field or method in a class.
 * - Specify the desired course code prefix using the {@code value} attribute.
 * <p>
 * Example:
 * <pre>
 * {@literal @}CourseCode(value = "CS", message = "Must start with CS")
 * private String courseCode;
 * </pre>
 *
 * Annotation Metadata:
 * - {@code @Constraint}: Associates this annotation with the validation logic class
 *   {@link CourseCodeConstraintValidator}.
 * - {@code @Target}: Specifies the annotation can be applied to fields and methods.
 * - {@code @Retention}: Indicates the annotation is available at runtime for reflection.
 */
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    /**
     * Defines the default prefix for the course code.
     * <p>
     * By default, the course code must start with "LUV".
     *
     * @return The required prefix for the course code.
     */
    String value() default "LUV";

    /**
     * Defines the default error message displayed when validation fails.
     *
     * @return The error message.
     */
    String message() default "Must start with LUV";

    /**
     * Defines default validation groups.
     * <p>
     * Groups allow constraints to be applied selectively in validation scenarios.
     *
     * @return The group or groups the constraint belongs to.
     */
    Class<?>[] groups() default {};

    /**
     * Defines default payload for clients to specify custom error metadata.
     * <p>
     * Payloads can provide additional information about the validation failure,
     * such as severity level or error codes.
     *
     * @return An array of {@link Payload} types.
     */
    Class<? extends Payload>[] payload() default {};
}
