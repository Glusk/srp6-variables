package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;
import static java.nio.ByteOrder.BIG_ENDIAN;

import java.math.BigInteger;
import java.util.Arrays;

import com.github.glusk.caesar.Bytes;

/**
 * A pre-set SRP-6 variable.
 * <p>
 * Objects of this class can be used to mock a variable for testing purposes or
 * to represent constants.
 */
public final class SRP6PresetVariable implements SRP6Variable {
    /** Pre-set byte sequence that represents {@code this} variable. */
    private final Bytes bytes;
    /** The byte order of {@code bytes}. */
    private final ByteOrder order;

    /**
     * Creates a new pre-set SRP-6 variable from a {@code BigInteger}.
     * <p>
     * Equivalent to:
     * <pre>
     * new SRP6PresetVariable(
     *     () -&gt; bi.toByteArray(),
     *     ByteOrder.BIG_ENDIAN
     * )
     * </pre>
     *
     * @param bi pre-set value {@code BigInteger} argument
     */
    public SRP6PresetVariable(final BigInteger bi) {
        this(() -> bi.toByteArray(), BIG_ENDIAN);
    }

    /**
     * Creates a new pre-set SRP-6 variable from the specified byte sequence
     * and the desired byte {@code order}.
     *
     * @param bytes pre-set byte sequence that represents {@code this} variable
     * @param order the byte order of {@code bytes}
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6PresetVariable(final Bytes bytes, final ByteOrder order) {
        this.bytes = bytes;
        this.order = order;
    }

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        // (1) Grab big-endian representation, else reverse
        Bytes minimal = order.equals(BIG_ENDIAN) ? bytes : bytes.reversed();

        // (2) Trim leading zeroes
        byte[] tmp = minimal.asArray();
        int i = 0;
        while (i < tmp.length && tmp[i] == 0) {
            i++;
        }
        if (i == 0) {
            minimal = () -> tmp;
        } else {
            final int offSet = i;
            minimal = () -> Arrays.copyOfRange(tmp, offSet, tmp.length);
        }

        // (3) If reversed in (1), reverse again
        minimal = order.equals(BIG_ENDIAN) ? minimal : minimal.reversed();

        // (4) Return minimal representation in preferred order
        if (order.equals(preferredOrder)) {
            return minimal;
        }
        return minimal.reversed();
    }
}
