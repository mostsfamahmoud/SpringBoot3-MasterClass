package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validation logic for the {@link CourseCode} annotation.
 * <p>
 * This class implements {@link ConstraintValidator} to define the rules for validating
 * course codes. It checks whether a given string starts with the prefix specified
 * in the `@CourseCode` annotation.
 *
 * <p>
 * The {@code initialize} method is called once to initialize the validator with
 * the prefix specified in the annotation. The {@code isValid} method contains
 * the actual validation logic, which verifies if the input starts with the specified prefix.
 */
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    // Holds the course code prefix defined in the annotation
    private String coursePrefix;

    /**
     * Initializes the validator with the prefix specified in the {@link CourseCode} annotation.
     * <p>
     * This method is called once during the validation lifecycle and sets the coursePrefix
     * based on the annotation's `value` attribute.
     *
     * @param courseCode The {@link CourseCode} annotation instance containing the configuration.
     */
    @Override
    public void initialize(CourseCode courseCode) {
        // Assign the value specified in the annotation (e.g., "LUV")
        coursePrefix = courseCode.value();
    }

    /**
     * Validates the input string to check if it starts with the specified prefix.
     * <p>
     * This method is called for each validation request. If the input string is non-null,
     * it verifies whether the string starts with the defined prefix.
     * <p>
     * If the input is null, it is considered valid by default (as null checks are typically
     * handled separately by other annotations such as {@code @NotNull}).
     *
     * @param theCode                   The value entered in the form field being validated.
     * @param constraintValidatorContext Context for reporting validation errors.
     *                                   Can be used to add custom error messages.
     * @return {@code true} if the input is valid; {@code false} otherwise.
     */
    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {

        // If the input is not null, check if it starts with the specified prefix
        if (theCode != null) {
            return theCode.startsWith(coursePrefix);
        }

        // Null values are considered valid (to be handled by other annotations)
        return true;
    }
}
