package com.github.glusk.srp6_variables;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.SecureRandom;

import com.github.glusk.caesar.Bytes;

/**
 * SRP-6 Random Ephemeral (a, b).
 * <p>
 * Use this class to generate random private ephemeral values {@code a} and
 * {@code b} (see {@link com.github.glusk.srp6_variables package docs}).
 * <p>
 * This class generates a random value {@code r} of the form:
 * <pre>
 * 1 &lt; r &lt; N
 * </pre>
 * where {@code N} is the SRP-6 "prime" variable.
 * <p>
 * The bit-length of this variable will be at least 256 bits, as recommended in:
 * <a href="https://tools.ietf.org/html/rfc5054#section-3">RFC 5054 | 3.  Security Considerations</a>.
 * <p>
 * This class is thread-safe.
 */
public final class SRP6RandomEphemeral implements SRP6IntegerVariable {
    /** The minimal bit length recommended in RFC 5054. */
    private static final int MIN_BIT_LENGTH = 256;

    /** Source of randomness. */
    private final SecureRandom rng;
    /** The bit-length of {@code this} variable. */
    private final int bitLength;
    /** SRP-6 Integer Variable: prime (N). */
    private final SRP6IntegerVariable prime;

    /** Cached value that represents this object. */
    private SRP6IntegerVariable cached;

    /**
     * Creates a new SRP-6 Random Ephemeral.
     *
     * @param rng source of randomness
     * @param desiredBitLength the desired bit-length of {@code this} variable
     * @param prime SRP-6 Integer Variable: prime (N)
     * @throws IllegalArgumentException If the set bit length is greater
     *                                  than the bit length of {@code prime}.
     *                                  More formally if:
     *               {@code Math.max(desiredBitLength, 256) > bitlength(prime)}
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6RandomEphemeral(
        final SecureRandom rng,
        final int desiredBitLength,
        final SRP6IntegerVariable prime
    ) throws IllegalArgumentException {
        this.rng = rng;
        this.bitLength = Math.max(desiredBitLength, MIN_BIT_LENGTH);
        if (this.bitLength > prime.asNonNegativeBigInteger().bitLength()) {
            throw new IllegalArgumentException(
                "The desired bit-length is greater than the bit-length of "
              + "prime (N)."
            );
        }
        this.prime = prime;
    }

    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public synchronized Bytes bytes(final ByteOrder preferredOrder) {
        if (cached == null) {
            /*
             The algorithm is takes ideas from the Stanford's SRP Java library
             (srp-2.1.2), available at: http://srp.stanford.edu/download.html
             */
            BigInteger N = prime.asNonNegativeBigInteger();
            BigInteger r;
            do {
                r = new BigInteger(bitLength, rng);
            } while (
                r.compareTo(BigInteger.ONE) <= 0
             || r.compareTo(N) >= 0
             || r.bitLength() < bitLength
            );
            cached = new SRP6CustomIntegerVariable(r);
        }
        return cached.bytes(preferredOrder);
    }
}