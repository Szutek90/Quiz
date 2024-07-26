package com.app.validator.impl;
import com.app.validator.Validator;

import java.util.function.Predicate;

/**
 * The StringValidator class implements the Validator interface for String objects.
 * It provides methods for validating String objects based on user-defined predicates.
 */
public class StringValidator implements Validator<String> {

    /**
     * Validates the specified String object using the provided predicate.
     * @param predicate The predicate to apply for validation.
     * @param value The String value to validate.
     */
    @Override
    public void validate(Predicate<String> predicate, String value) {
        Validator.super.validate(predicate, value); // Calls the default implementation of validate() in the Validator interface
    }

    /**
     * Checks if the specified String is empty.
     * @param text The String to check.
     */
    public static void isEmpty(String text) {
        new StringValidator().validate(String::isEmpty, text); // Validates if the String is empty using a predicate
    }
}

