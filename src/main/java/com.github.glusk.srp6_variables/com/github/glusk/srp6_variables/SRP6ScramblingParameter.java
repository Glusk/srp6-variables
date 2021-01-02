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
public final class SRP6ScramblingParameter implements SRP6IntegerVariable {
    /** SRP-6 Integer Variable: random scrambling parameter (u). */
    private final SRP6IntegerVariable scramblingParameter;

    /**
     * Constructs a new SRP-6 Random Scrambling Parameter.
     * <pre>
     * u = H(A | B)
     * </pre>
     *
     * @param hashFunction a one-way hash function - H()
     * @param clientPublicKey SRP-6 variable: client public key (A)
     * @param serverPublicKey SRP-6 variable: server public key (B)
     * @param endianness the byte order to use when converting the resulting
     *                   hash to integer and the byte order of public keys'
     *                   byte sequences to feed to the hash function
     */
    public SRP6ScramblingParameter(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable clientPublicKey,
        final SRP6IntegerVariable serverPublicKey,
        final ByteOrder endianness
    ) {
        this(
            new SRP6CustomIntegerVariable(
                new Hash(
                    hashFunction,
                    clientPublicKey.bytes(endianness),
                    serverPublicKey.bytes(endianness)
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
