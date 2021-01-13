package com.github.glusk.srp6_variables;

/**
 * An SRP-6 Padding Exception.
 * <p>
 * This exception is thrown when trying to cut off byte sequences returned by
 * {@link SRP6IntegerVariable#bytes(ByteOrder)} to a shorter length instead of
 * zero-padding them to longer ones.
 * <p>
 * For example:
 * <pre>
 * try {
 *     new SRP6CustomIntegerVariable(
 *         () -&gt; new byte[] {1, 2, 0},
 *         ByteOrder.BIG_ENDIAN
 *     ).bytes(ByteOrder.BIG_ENDIAN, 2);
 * } catch (SRP6PaddingException e) {
 *     // An exception is thrown!
 * }
 * </pre>
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
