package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;
import java.math.BigInteger;

import com.github.glusk.caesar.Bytes;

/**
 * SRP-6 Verifier (v).
 * <p>
 * This variable is computed as:
 * <pre>
 * v = g^x
 * </pre>
 * where {@code ^} is modular exponentiation modulo {@code N}.
 * <p>
 * Refer to the  {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6Verifier implements SRP6Variable {
    /** SRP-6 Variable: prime (N). */
    private final SRP6Variable prime;
    /** SRP-6 Variable: generator (g). */
    private final SRP6Variable generator;
    /** SRP-6 Variable: private key (x). */
    private final SRP6Variable privateKey;

    /**
     * Constructs a new SRP-6 Verifier from prime, generator and private key.
     *
     * @param prime SRP-6 Variable: prime (N)
     * @param generator SRP-6 Variable: generator (g)
     * @param privateKey SRP-6 Variable: private key (x)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6Verifier(
        final SRP6Variable prime,
        final SRP6Variable generator,
        final SRP6Variable privateKey
    ) {
        this.prime = prime;
        this.generator = generator;
        this.privateKey = privateKey;
    }

    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public Bytes bytes(final ByteOrder preferredOrder) {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger g = generator.asNonNegativeBigInteger();
        BigInteger x = privateKey.asNonNegativeBigInteger();

        BigInteger v = g.modPow(x, N);

        return new SRP6PresetVariable(v).bytes(preferredOrder);
    }
}
