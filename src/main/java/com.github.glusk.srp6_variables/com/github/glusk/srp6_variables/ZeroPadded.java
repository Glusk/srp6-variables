package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.AbstractBytes;

/** A zero-padded SRP-6 Integer Variable Bytes view. */
public final class ZeroPadded extends AbstractBytes {
    /** An SRP-6 Integer Variable to zero-pad. */
    private final SRP6IntegerVariable variableToZeroPad;
    /**
     * The byte order to use when converting {@code variableToZeroPad}
     * to Bytes.
     */
    private final ByteOrder order;
    /**
     * {@code variableToZeroPad} will be zero-padded to
     * {@code variableToPadTo.bytes(order).asArray().length}.
     */
    private final SRP6IntegerVariable variableToPadTo;

    /**
     * Creates a new ZeroPadded Bytes object by wrapping
     * {@code variableToZeroPad}, {@code order}, and {@code} variableToPadTo}.
     *
     * @param variableToZeroPad an SRP-6 Integer Variable to zero-pad
     * @param order the byte order to use when converting
     *              {@code variableToZeroPad} to Bytes
     * @param variableToPadTo {@code variableToZeroPad} will be zero-padded to
     *                    {@code variableToPadTo.bytes(order).asArray().length}
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public ZeroPadded(
        final SRP6IntegerVariable variableToZeroPad,
        final ByteOrder order,
        final SRP6IntegerVariable variableToPadTo
    ) {
        this.variableToZeroPad = variableToZeroPad;
        this.order = order;
        this.variableToPadTo = variableToPadTo;
    }
    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException If {@code variableToZeroPad} has a
     * greater byte length than {@code variableToPadTo}, an
     * {@code SRP6PaddingException} gets thrown. This method catches it and
     * re-throws it as and {@code IllegalArgumentException}. More info:
     * {@linkplain SRP6IntegerVariable#bytes(ByteOrder,int)}
     */
    @Override
    public byte[] asArray() {
        try {
            return this.variableToZeroPad.bytes(
                this.order,
                this.variableToPadTo.bytes(this.order).asArray().length
            ).asArray();
        } catch (SRP6PaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
