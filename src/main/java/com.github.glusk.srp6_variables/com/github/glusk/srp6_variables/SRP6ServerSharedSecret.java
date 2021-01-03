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
public final class SRP6ServerSharedSecret implements SRP6IntegerVariable {
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
     * @throws SRP6SecurityException if {@code A == 0 (mod N)}
     */
    @SuppressWarnings({
        "checkstyle:hiddenfield",
        "checkstyle:localvariablename"
    })
    public SRP6ServerSharedSecret(
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable clientPublicKey,
        final SRP6IntegerVariable verifier,
        final SRP6IntegerVariable scramblingParameter,
        final SRP6IntegerVariable serverEphPrvtKey
    ) throws SRP6SecurityException {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger A = clientPublicKey.asNonNegativeBigInteger();
        if (A.mod(N).equals(BigInteger.ZERO)) {
            throw new SRP6SecurityException(
                "Invalid parameters: A == 0 (mod N)"
            );
        }

        this.prime = prime;
        this.clientPublicKey = clientPublicKey;
        this.verifier = verifier;
        this.scramblingParameter = scramblingParameter;
        this.serverEphPrvtKey = serverEphPrvtKey;
    }

    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public Bytes bytes(final ByteOrder preferredOrder) {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger A = clientPublicKey.asNonNegativeBigInteger();
        BigInteger v = verifier.asNonNegativeBigInteger();
        BigInteger u = scramblingParameter.asNonNegativeBigInteger();
        BigInteger b = serverEphPrvtKey.asNonNegativeBigInteger();

        // S = (Av^u) ^ b
        BigInteger S = A.multiply(v.modPow(u, N)).modPow(b, N);

        return new SRP6CustomIntegerVariable(S).bytes(preferredOrder);
    }
}
