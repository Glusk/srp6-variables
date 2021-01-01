package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;
import java.math.BigInteger;

import com.github.glusk.caesar.Bytes;

/**
 * SRP-6 Client Public Key (A).
 * <p>
 * This variable is computed as:
 * <pre>
 * A = g^a
 * </pre>
 * where {@code ^} is modular exponentiation modulo {@code N}.
 * <p>
 * Refer to the  {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6ClientPublicKey implements SRP6IntegerVariable {
    /** SRP-6 Integer Variable: prime (N). */
    private final SRP6IntegerVariable prime;
    /** SRP-6 Integer Variable: generator (g). */
    private final SRP6IntegerVariable generator;
    /** SRP-6 Integer Variable: client ephemeral private key (a). */
    private final SRP6IntegerVariable clientEphPrvtKey;

    /**
     * Constructs a new SRP-6 Verifier from prime, generator and client
     * ephemeral private key.
     *
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param generator SRP-6 Integer Variable: generator (g)
     * @param clientEphPrvtKey SRP-6 Integer Variable: client ephemeral private
     *                         key (a)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6ClientPublicKey(
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable generator,
        final SRP6IntegerVariable clientEphPrvtKey
    ) {
        this.prime = prime;
        this.generator = generator;
        this.clientEphPrvtKey = clientEphPrvtKey;
    }

    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public Bytes bytes(final ByteOrder preferredOrder) {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger g = generator.asNonNegativeBigInteger();
        BigInteger a = clientEphPrvtKey.asNonNegativeBigInteger();

        BigInteger A = g.modPow(a, N);

        return new SRP6CustomIntegerVariable(A).bytes(preferredOrder);
    }
}
