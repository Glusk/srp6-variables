package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Xor;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

/**
 * SRP-6 Client Session Proof (M1).
 * <p>
 * This variable is computed as either (1):
 * <pre> M1 = H(H(N) xor H(g), H(I), s, A, B, K) </pre>
 * or (2): <pre> M1 = H(A, B, S) </pre>
 * Use (1) to prove a shared, strong session key {@code K}.
 * <p>
 * Use (2) in a password-only proof where the calculation of {@code K} can be
 * skipped.
 * <p>
 * Refer to the {@link com.github.glusk.srp6_variables package docs} for more
 * info on notation used.
 */
public final class SRP6ClientSessionProof extends AbstractBytes {
    /** SRP-6 Variable: client session proof (M1). */
    private final Bytes clientSessionProof;

    /**
     * Creates a new SRP-6 Client Session Proof that proofs the existence of a
     * shared, strong session key {@code K}.
     * <p>
     * The formula used is as follows:
     * <pre>
     * M1 = H(H(N) xor H(g), H(I), s, PAD(A), PAD(B), K)
     * </pre>
     * Client public key (A) and server public key (B) are zero-padded to the
     * byte length of prime (N).
     *
     * @param hashFunction a one-way hash function - H()
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param generator SRP-6 Integer Variable: generator (g)
     * @param identity SRP-6 variable: cleartext username - identity (I)
     * @param salt SRP-6 Variable: salt (s)
     * @param clientPublicKey SRP-6 Integer Variable: client public key (A)
     * @param serverPublicKey SRP-6 Integer Variable: server public key (B)
     * @param sessionKey SRP-6 Variable: session key (K)
     * @param byteOrder the byte order to use when converting SRP-6 Integer
     *                  Variables to a byte sequence
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public SRP6ClientSessionProof(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable generator,
        final Bytes identity,
        final Bytes salt,
        final SRP6IntegerVariable clientPublicKey,
        final SRP6IntegerVariable serverPublicKey,
        final Bytes sessionKey,
        final ByteOrder byteOrder
    ) {
        this(
            new Hash(
                hashFunction,
                new Xor(
                    new Hash(
                        hashFunction,
                        new BytesView(prime, byteOrder)
                    ),
                    new Hash(
                        hashFunction,
                        new BytesView(generator, byteOrder)
                    )
                ),
                new Hash(hashFunction, identity),
                salt,
                new ZeroPadded(clientPublicKey, byteOrder, prime),
                new ZeroPadded(serverPublicKey, byteOrder, prime),
                sessionKey
            )
        );
    }

    /**
     * Creates a new SRP-6 password-only Client Session Proof where the
     * calculation of {@code K} can be skipped.
     * <p>
     * The formula used is as follows:
     * <pre>
     * M1 = H(PAD(A), PAD(B), PAD(S))
     * </pre>
     * Client public key (A), server public key (B) and shared secret (S) are
     * zero-padded to the byte length of prime (N).
     *
     * @param hashFunction a one-way hash function - H()
     * @param prime SRP-6 Integer Variable: prime (N)
     * @param clientPublicKey SRP-6 Integer Variable: client public key (A)
     * @param serverPublicKey SRP-6 Integer Variable: server public key (B)
     * @param sharedSecret SRP-6 Variable: shared secret (S)
     * @param byteOrder the byte order to use when converting SRP-6 Integer
     *                  Variables to a byte sequence
     */
    public SRP6ClientSessionProof(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable prime,
        final SRP6IntegerVariable clientPublicKey,
        final SRP6IntegerVariable serverPublicKey,
        final SRP6IntegerVariable sharedSecret,
        final ByteOrder byteOrder
    ) {
        this(
            new Hash(
                hashFunction,
                new ZeroPadded(clientPublicKey, byteOrder, prime),
                new ZeroPadded(serverPublicKey, byteOrder, prime),
                new ZeroPadded(sharedSecret, byteOrder, prime)
            )
        );
    }

    /**
     * Hidden constructor.
     * <p>
     * Users should not be able to set this variable directly.
     *
     * @param clientSessionProof SRP-6 Variable: client session proof (M1)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    private SRP6ClientSessionProof(final Bytes clientSessionProof) {
        this.clientSessionProof = clientSessionProof;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if byte length of {@code N} is shorter
     *                              than the byte length of either {@code A}
     *                              {@code B}, or {@code S}
     */
    @Override
    public byte[] asArray() throws IllegalStateException {
        return clientSessionProof.asArray();
    }
}
