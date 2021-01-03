package com.github.glusk.srp6_variables;

/**
 * An SRP-6 Exception.
 * <p>
 * This exception indicates a general protocol error.
 */
public class SRP6Exception extends Exception {
    /**
     * Creates a new SRP-6 Exception.
     *
     * @param reason a brief explanation as to why this exception was raised
     */
    public SRP6Exception(final String reason) {
        super(reason);
    }
}
