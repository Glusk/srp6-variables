package com.github.glusk.srp6_variables;

/**
 * An SRP-6 Padding Exception.
 * <p>
 * This exception indicates a padding error.
 */
public final class SRP6PaddingException extends SRP6Exception {
    /**
     * Creates a new SRP-6 Padding Exception.
     *
     * @param reason a brief explanation as to why this exception was raised
     */
    public SRP6PaddingException(final String reason) {
        super(reason);
    }
}
