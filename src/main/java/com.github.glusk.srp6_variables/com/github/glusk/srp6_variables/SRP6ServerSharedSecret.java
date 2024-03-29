package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;
import java.math.BigInteger;

import com.github.glusk.caesar.Bytes;

/**
 * SRP-6 Server Shared Secret (S).
 * <p>
 * This variable is computed as:
 * <pre>
 * S = (Av^u) ^ b
 * </pre>
 * where {@code ^} is modular exponentiation modulo {@code N}.
 * <p>
 * RFC 5054 also calls this value "premaster secret".
 * <p>
 * Refer to the  {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6ServerSharedSecret extends AbstractSRP6IntegerVariable {
    /** SRP-6 Integer Variable: prime (N). */
    private final SRP6IntegerVariable prime;
    /** SRP-6 Integer Variable: client public key (A). */
    private final SRP6IntegerVariable clientPublicKey;
    /** SRP-6 Integer Variable: verifier (v). */
    private final SRP6IntegerVariable verifier;
    /** SRP-6 Integer Variable: random scrambling parameter (u). */
    private final SRP6IntegerVariable scramblingParameter;
    /** SRP-6 Integer Variable: server ephemeral private key (b). */
    private final SRP6IntegerVariable serverEphPrvtKey;

    /**
     * Constructs a new SRP-6 Server Shared Secret from prime, client public
     * key, verifier, random scrambling parameter and server ephemeral private
     * key.
     *
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param clientPublicKey SRP-6 Integer Variable: client public key (A)
     * @param verifier SRP-6 Integer Variable: verifier (v)
     * @param scramblingParameter SRP-6 Integer Variable: random scrambling
     *                            parameter (u)
     * @param serverEphPrvtKey SRP-6 Integer Variable: server ephemeral private
     *                         key (b)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6ServerSharedSecret(
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable clientPublicKey,
        final SRP6IntegerVariable verifier,
        final SRP6IntegerVariable scramblingParameter,
        final SRP6IntegerVariable serverEphPrvtKey
    ) {
        this.prime = prime;
        this.clientPublicKey = clientPublicKey;
        this.verifier = verifier;
        this.scramblingParameter = scramblingParameter;
        this.serverEphPrvtKey = serverEphPrvtKey;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if {@code A == 0 (mod N)}
     */
    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public Bytes bytes(final ByteOrder preferredOrder)
        throws IllegalStateException {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger A = clientPublicKey.asNonNegativeBigInteger();
        BigInteger v = verifier.asNonNegativeBigInteger();
        BigInteger u = scramblingParameter.asNonNegativeBigInteger();
        BigInteger b = serverEphPrvtKey.asNonNegativeBigInteger();

        if (A.mod(N).equals(BigInteger.ZERO)) {
            throw new IllegalStateException(
                new SRP6SecurityException(
                    "Invalid parameters: A == 0 (mod N)"
                )
            );
        }

        // S = (Av^u) ^ b
        BigInteger S = A.multiply(v.modPow(u, N)).modPow(b, N);

        return new SRP6CustomIntegerVariable(S).bytes(preferredOrder);
    }
}
