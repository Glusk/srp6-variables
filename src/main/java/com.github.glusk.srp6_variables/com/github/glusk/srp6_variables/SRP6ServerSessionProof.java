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
     * Creates a new SRP-6 Server Session Proof from the specified hash
     * function, client's public key and its preferred byte order, client's
     * session proof and session key.
     * <p>
     * When converting client public key to a byte sequence, this constructor
     * will use the <em>minimal</em> representation (see
     * {@link SRP6IntegerVariable#bytes(ByteOrder)}).
     *
     * @param hashFunction a one-way hash function - H()
     * @param clientPublicKey SRP-6 Integer Variable: client public key (A)
     * @param clientProof SRP-6 Variable: client session proof (M1)
     * @param sessionKey SRP-6 Variable: session key (K)
     * @param byteOrder the byte order to use when converting
     *                  {@code clientPublicKey} to a byte sequence
     */
    public SRP6ServerSessionProof(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable clientPublicKey,
        final Bytes clientProof,
        final Bytes sessionKey,
        final ByteOrder byteOrder
    ) {
        this(
            new Hash(
                hashFunction,
                clientPublicKey.bytes(byteOrder),
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
