package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

/**
 * SRP-6 Random Scrambling Parameter (u).
 * <p>
 * This variable is computed as:
 * <pre>
 * u = H(A | B)
 * </pre>
 * where {@code H()} is a one-way hash function, {@code |} a concatenation
 * operator, {@code A} client's public key and {@code B} the server's public
 * key.
 */
public final class SRP6ScramblingParameter extends AbstractSRP6IntegerVariable {
    /** SRP-6 Integer Variable: random scrambling parameter (u). */
    private final SRP6IntegerVariable scramblingParameter;

    /**
     * Constructs a new SRP-6 Random Scrambling Parameter as specified in
     * RFC 5054.
     * <pre>
     * u = H(PAD(A) | PAD(B))
     * </pre>
     * <strong>Note:</strong> {@code A} and {@code B} are zero-padded to the
     * byte length of {@code N}.
     *
     * @param hashFunction a one-way hash function - H()
     * @param clientPublicKey SRP-6 variable: client public key (A)
     * @param serverPublicKey SRP-6 variable: server public key (B)
     * @param prime SRP-6 variable: prime (N)
     * @param endianness the byte order to use when converting the resulting
     *                   hash to integer and the byte order of public keys'
     *                   byte sequences to feed to the hash function
     * @throws SRP6PaddingException if byte length of {@code N} is shorter
     *                              than the byte length of either {@code A} or
     *                              {@code B}
     */
    public SRP6ScramblingParameter(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable clientPublicKey,
        final SRP6IntegerVariable serverPublicKey,
        final SRP6IntegerVariable prime,
        final ByteOrder endianness
    ) throws SRP6PaddingException {
        this(
            new SRP6CustomIntegerVariable(
                new Hash(
                    hashFunction,
                    clientPublicKey.bytes(
                        endianness,
                        prime.bytes(endianness).asArray().length
                    ),
                    serverPublicKey.bytes(
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
     * @param scramblingParameter SRP-6 Integer Variable: random scrambling
     *                            parameter (u)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    private SRP6ScramblingParameter(
        final SRP6IntegerVariable scramblingParameter
    ) {
        this.scramblingParameter = scramblingParameter;
    }

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return scramblingParameter.bytes(preferredOrder);
    }
}
