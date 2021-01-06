package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

/**
 * SRP-6 Session Key (K).
 * <p>
 * This variable is computed as:
 * <pre>
 * K = H_Inteleave(S)
 * </pre>
 * where {@code S} is a shared secret and {@code H_Interleave} a special
 * hashing function, used to generate the session key that is twice as long.
 * <p>
 * This implementation closely follows the algorithm described in RFC 2945,
 * <a href="https://tools.ietf.org/html/rfc2945#section-3.1">Section 3.1 - Interleaved SHA</a>.
 * <p>
 * The underlying hash function need not be SHA-1.
 */
public final class SRP6SessionKey implements Bytes {
    /**
     * The hash function used to perform the hashing inside the H_Interleave
     * algorithm.
     */
    private final ImmutableMessageDigest hashFunction;
    /** SRP-6 variable: shared secret (S). */
    private final SRP6IntegerVariable sharedSecret;
    /**
     * The byte order to use when converting {@code sharedSecret} to a byte
     * sequence.
     */
    private final ByteOrder byteOrder;

    /**
     * Creates a new SRP-6 Session Key from the specified hash function, shared
     * secret and its preferred byte order.
     *
     * @param hashFunction the hash function used to perform the hashing inside
     *                     the H_Interleave algorithm
     * @param sharedSecret SRP-6 variable: shared secret (S)
     * @param byteOrder the byte order to use when converting
     *                  {@code sharedSecret} to a byte sequence
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public SRP6SessionKey(
        final ImmutableMessageDigest hashFunction,
        final SRP6IntegerVariable sharedSecret,
        final ByteOrder byteOrder
    ) {
        this.hashFunction = hashFunction;
        this.sharedSecret = sharedSecret;
        this.byteOrder = byteOrder;
    }

    @Override
    public byte[] asArray() {
        byte[] t = sharedSecret.bytes(byteOrder).asArray();
        int off = t.length % 2;
        int halfSize = t.length / 2;

        byte[] e = new byte[halfSize];
        byte[] f = new byte[halfSize];
        for (int i = off; i < halfSize; i++) {
            e[i - off] = t[2 * i     - off];
            f[i - off] = t[2 * i + 1 - off];
        }
        byte[] g = hashFunction.update(e).digest();
        byte[] h = hashFunction.update(f).digest();

        byte[] res = new byte[g.length + h.length];
        for (int i = 0; i < res.length / 2; i++) {
            res[2 * i    ] = g[i];
            res[2 * i + 1] = h[i];
        }
        return res;
    }
}
