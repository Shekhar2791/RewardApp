package com.cg.reward.calculator.exception;
/**
 * Custom exception thrown when a requested resource is not found.
 * Used for customer or transaction lookup failures.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates the exception with a message.
     * @param message description of the failure
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
