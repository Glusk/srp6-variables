package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

/**
 * SRP-6 Server Session Proof (M2).
 * <p>
 * This variable is computed as:
 * <pre>
 * M2 = H(A, M1, K)
 * </pre>
 * Refer to the {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6ServerSessionProof implements Bytes {
    /** SRP-6 Variable: server session proof (M2). */
    private final Bytes serverSessionProof;

    /**
     * Creates a new SRP-6 Server Session Proof by zero-padding the client's
     * public key.
     * <p>
     * The formula used is as follows:
     * <pre>
     * M2 = H(PAD(A), M1, K)
     * </pre>
     * Client public key (A) is zero-padded to the byte length of prime (N).
     *
     * @param hashFunction a one-way hash function - H()
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param clientPublicKey SRP-6 Integer Variable: client public key (A)
     * @param clientProof SRP-6 Variable: client session proof (M1)
     * @param sessionKey SRP-6 Variable: session key (K)
     * @param byteOrder the byte order to use when converting
     *                  {@code clientPublicKey} to a byte sequence
     * @throws SRP6PaddingException if byte length of {@code N} is shorter
     *                              than the byte length of {@code A}
     */
    public SRP6ServerSessionProof(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable clientPublicKey,
        final Bytes clientProof,
        final Bytes sessionKey,
        final ByteOrder byteOrder
    ) throws SRP6PaddingException {
        this(
            new Hash(
                hashFunction,
                clientPublicKey.bytes(
                    byteOrder,
                    prime.bytes(byteOrder).asArray().length
                ),
                clientProof,
                sessionKey
            )
        );
    }

    /**
     * Hidden constructor.
     * <p>
     * Users should not be able to set this variable directly.
     *
     * @param serverSessionProof SRP-6 Variable: server session proof (M2)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    private SRP6ServerSessionProof(final Bytes serverSessionProof) {
        this.serverSessionProof = serverSessionProof;
    }

    @Override
    public byte[] asArray() {
        return serverSessionProof.asArray();
    }
}
