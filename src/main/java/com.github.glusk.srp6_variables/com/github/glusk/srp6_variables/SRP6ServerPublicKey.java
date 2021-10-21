package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;
import java.math.BigInteger;

import com.github.glusk.caesar.Bytes;

/**
 * SRP-6 Server Public Key (B).
 * <p>
 * This variable is computed as:
 * <pre>
 * B = k*v + g^b
 * </pre>
 * where {@code ^} is modular exponentiation modulo {@code N}.
 * <p>
 * Refer to the  {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6ServerPublicKey extends AbstractSRP6IntegerVariable {
    /** SRP-6 Integer Variable: prime (N). */
    private final SRP6IntegerVariable prime;
    /** SRP-6 Integer Variable: generator (g). */
    private final SRP6IntegerVariable generator;
    /** SRP-6 Integer Variable: multiplier (k). */
    private final SRP6IntegerVariable multiplier;
    /** SRP-6 Integer Variable: verifier (v). */
    private final SRP6IntegerVariable verifier;
    /** SRP-6 Integer Variable: server ephemeral private key (b)). */
    private final SRP6IntegerVariable serverEphPrvtKey;

    /**
     * Constructs a new SRP-6 Server Public Key from prime, generator,
     * multiplier, verifier and server ephemeral private key.
     *
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param generator SRP-6 Integer Variable: generator (g)
     * @param multiplier SRP-6 Integer Variable: multiplier (k)
     * @param verifier SRP-6 Integer Variable: verifier (v)
     * @param serverEphPrvtKey SRP-6 Integer Variable: server ephemeral private
     *                         key (b)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6ServerPublicKey(
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable generator,
        final SRP6IntegerVariable multiplier,
        final SRP6IntegerVariable verifier,
        final SRP6IntegerVariable serverEphPrvtKey
    ) {
        this.prime = prime;
        this.generator = generator;
        this.multiplier = multiplier;
        this.verifier = verifier;
        this.serverEphPrvtKey = serverEphPrvtKey;
    }

    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public Bytes bytes(final ByteOrder preferredOrder) {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger g = generator.asNonNegativeBigInteger();
        BigInteger k = multiplier.asNonNegativeBigInteger();
        BigInteger v = verifier.asNonNegativeBigInteger();
        BigInteger b = serverEphPrvtKey.asNonNegativeBigInteger();

        BigInteger B = k.multiply(v).add(g.modPow(b, N)).mod(N);

        return new SRP6CustomIntegerVariable(B).bytes(preferredOrder);
    }
}
