package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.AbstractBytes;

/** An SRP-6 Integer Variable Bytes view. */
public final class BytesView extends AbstractBytes {
    /** An SRP-6 Integer Variable to view as Bytes. */
    private final SRP6IntegerVariable variableToViewAsBytes;
    /**
     * The byte order to use when converting {@code variableToViewAsBytes}
     * to Bytes.
     */
    private final ByteOrder order;

    /**
     * Creates a new SRP-6 Integer Variable Bytes view by wrapping
     * {@code variableToViewAsBytes} and {@code} order}.
     *
     * @param variableToViewAsBytes an SRP-6 Integer Variable to view as Bytes
     * @param order the byte order to use when converting
     *              {@code variableToViewAsBytes} to Bytes
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public BytesView(
        final SRP6IntegerVariable variableToViewAsBytes,
        final ByteOrder order
    ) {
        this.variableToViewAsBytes = variableToViewAsBytes;
        this.order = order;
    }
    @Override
    public byte[] asArray() {
        return this.variableToViewAsBytes.bytes(this.order).asArray();
    }
}
