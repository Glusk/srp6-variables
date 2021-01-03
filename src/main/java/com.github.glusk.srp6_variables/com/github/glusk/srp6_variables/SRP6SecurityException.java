package com.github.glusk.srp6_variables;

/**
 * An SRP-6 Security Exception.
 * <p>
 * This exception indicates a security error.
 */
public class SRP6SecurityException extends SRP6Exception {
    /**
     * Creates a new SRP-6 Security Exception.
     *
     * @param reason a brief explanation as to why this exception was raised
     */
    public SRP6SecurityException(final String reason) {
        super(reason);
    }
}
