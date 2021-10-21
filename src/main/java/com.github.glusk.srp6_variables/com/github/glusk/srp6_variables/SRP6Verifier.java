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
public final class SRP6Verifier extends AbstractSRP6IntegerVariable {
    /** SRP-6 Integer Variable: prime (N). */
    private final SRP6IntegerVariable prime;
    /** SRP-6 Integer Variable: generator (g). */
    private final SRP6IntegerVariable generator;
    /** SRP-6 Integer Variable: private key (x). */
    private final SRP6IntegerVariable privateKey;

    /**
     * Constructs a new SRP-6 Verifier from prime, generator and private key.
     *
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param generator SRP-6 Integer Variable: generator (g)
     * @param privateKey SRP-6 Integer Variable: private key (x)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6Verifier(
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable generator,
        final SRP6IntegerVariable privateKey
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

        return new SRP6CustomIntegerVariable(v).bytes(preferredOrder);
    }
}
