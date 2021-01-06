package com.github.glusk.srp6_variables;

import java.math.BigInteger;
import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

/**
 * SRP-6 Multiplier Parameter (k).
 * <p>
 * This variable is computed as:
 * <pre>
 * k = 3
 * </pre>
 * in SRP-6, and as:
 * <pre>
 * k = H(N | g)
 * </pre>
 * in SRP-6a.
 * <p>
 * Refer to the  {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6Multiplier implements SRP6IntegerVariable {
    /** SRP-6 Integer Variable: multiplier parameter (k). */
    private final SRP6IntegerVariable multiplier;

    /**
     * Constructs a new SRP-6 Multiplier Parameter.
     * <pre>
     * k = 3
     * </pre>
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public SRP6Multiplier() {
        this(new SRP6CustomIntegerVariable(BigInteger.valueOf(3)));
    }
    /**
     * Constructs a new SRP-6 Multiplier Parameter as specified in RFC 5054.
     * <pre>
     * k = H(N | PAD(g))
     * </pre>
     * <strong>Note:</strong> {@code g} is zero-padded to the byte length of
     * {@code N}.
     *
     * @param hashFunction a one-way hash function - H()
     * @param prime SRP-6 variable: prime (N)
     * @param generator SRP-6 variable: generator (g)
     * @param endianness the byte order to use when converting the resulting
     *                   hash to integer and the byte order of prime and
     *                   generator byte sequences to feed to the hash function
     * @throws SRP6PaddingException if byte length of {@code N} is shorter
     *                              than the byte length of {@code g}
     */
    public SRP6Multiplier(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable generator,
        final ByteOrder endianness
    ) throws SRP6PaddingException {
        this(
            new SRP6CustomIntegerVariable(
                new Hash(
                    hashFunction,
                    prime.bytes(endianness),
                    generator.bytes(
                        endianness,
                        prime.bytes(endianness).asArray().length
                    )
                ),
                endianness
            )
        );
    }

    /**
     * Hidden constructor.
     * <p>
     * Users should not be able to set this variable directly. Constants and
     * preset values must be created by using the
     * {@link SRP6CustomIntegerVariable} class.
     *
     * @param multiplier SRP-6 Integer Variable: multiplier parameter (k)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    private SRP6Multiplier(
        final SRP6IntegerVariable multiplier
    ) {
        this.multiplier = multiplier;
    }

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return multiplier.bytes(preferredOrder);
    }
}
