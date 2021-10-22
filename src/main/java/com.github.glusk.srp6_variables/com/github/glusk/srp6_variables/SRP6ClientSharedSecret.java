package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;
import java.math.BigInteger;

import com.github.glusk.caesar.Bytes;

/**
 * SRP-6 Client Shared Secret (S).
 * <p>
 * This variable is computed as:
 * <pre>
 * S = (B - kg^x) ^ (a + ux)
 * </pre>
 * where {@code ^} is modular exponentiation modulo {@code N}.
 * <p>
 * RFC 5054 also calls this value "premaster secret".
 * <p>
 * Refer to the  {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6ClientSharedSecret extends AbstractSRP6IntegerVariable {
    /** SRP-6 Integer Variable: prime (N). */
    private final SRP6IntegerVariable prime;
    /** SRP-6 Integer Variable: generator (g). */
    private final SRP6IntegerVariable generator;
    /** SRP-6 Integer Variable: multiplier (k). */
    private final SRP6IntegerVariable multiplier;
    /** SRP-6 Integer Variable: server public key (B). */
    private final SRP6IntegerVariable serverPublicKey;
    /** SRP-6 Integer Variable: private key (x). */
    private final SRP6IntegerVariable privateKey;
    /** SRP-6 Integer Variable: random scrambling parameter (u). */
    private final SRP6IntegerVariable scramblingParameter;
    /** SRP-6 Integer Variable: client ephemeral private key (a). */
    private final SRP6IntegerVariable clientEphPrvtKey;

    /**
     * Constructs a new SRP-6 Server Shared Secret from prime, generator,
     * multiplier, server public key, private key, random scrambling parameter
     * and client ephemeral private key.
     *
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param generator SRP-6 Integer Variable: generator (g)
     * @param multiplier SRP-6 Integer Variable: multiplier (k)
     * @param serverPublicKey SRP-6 Integer Variable: server public key (B)
     * @param privateKey SRP-6 Integer Variable: private key (x)
     * @param scramblingParameter SRP-6 Integer Variable: random scrambling
     *                            parameter (u)
     * @param clientEphPrvtKey SRP-6 Integer Variable: client ephemeral private
     *                         key (a)
     * @throws SRP6SecurityException if {@code B == 0 (mod N)} or
     *                               {@code u == 0}
     */
    @SuppressWarnings({
        "checkstyle:hiddenfield",
        "checkstyle:localvariablename"
    })
    public SRP6ClientSharedSecret(
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable generator,
        final SRP6IntegerVariable multiplier,
        final SRP6IntegerVariable serverPublicKey,
        final SRP6IntegerVariable privateKey,
        final SRP6IntegerVariable scramblingParameter,
        final SRP6IntegerVariable clientEphPrvtKey
    ) throws SRP6SecurityException {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger B = serverPublicKey.asNonNegativeBigInteger();
        if (B.mod(N).equals(BigInteger.ZERO)) {
            throw new SRP6SecurityException(
                "Invalid parameters: B == 0 (mod N)"
            );
        }
        BigInteger u = scramblingParameter.asNonNegativeBigInteger();
        if (u.mod(N).equals(BigInteger.ZERO)) {
            throw new SRP6SecurityException(
                "Invalid parameters: u == 0"
            );
        }

        this.prime = prime;
        this.generator = generator;
        this.multiplier = multiplier;
        this.serverPublicKey = serverPublicKey;
        this.privateKey = privateKey;
        this.scramblingParameter = scramblingParameter;
        this.clientEphPrvtKey = clientEphPrvtKey;
    }

    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public Bytes bytes(final ByteOrder preferredOrder) {
        BigInteger N = prime.asNonNegativeBigInteger();
        BigInteger g = generator.asNonNegativeBigInteger();
        BigInteger k = multiplier.asNonNegativeBigInteger();
        BigInteger B = serverPublicKey.asNonNegativeBigInteger();
        BigInteger x = privateKey.asNonNegativeBigInteger();
        BigInteger u = scramblingParameter.asNonNegativeBigInteger();
        BigInteger a = clientEphPrvtKey.asNonNegativeBigInteger();

        // S = (B - kg^x) ^ (a + ux)
        BigInteger S =
            B.subtract(
                k.multiply(g.modPow(x, N))
            ).modPow(
                a.add(u.multiply(x)),
                N
            );

        return new SRP6CustomIntegerVariable(S).bytes(preferredOrder);
    }
}
