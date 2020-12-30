package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

/**
 * SRP-6 Private Key (x).
 * <p>
 * This variable is computed as:
 * <pre>
 * x = H(s | p)
 * </pre>
 * where {@code H()} is a one-way hash function, {@code |} a concatenation
 * operator, {@code s} a random salt and {@code p} the client's password.
 * <p>
 * RFC 2945 further specifies {@code p} as:
 * <pre>
 * p = H(I | ":" | P)
 * </pre>
 * where {@code I} is cleartext username, or identity, and {@code P} cleartext
 * password. Use of {@code I} within {@code x} avoids a malicious server from
 * being able to learn if two users share the same password (refer to
 * <a href ="https://crypto.stackexchange.com/q/8626">this SO question</a> for
 * more info).
 * <p>
 * You are also permitted to compute this value as a custom hash:
 * <pre>
 * x = H(args)
 * </pre>
 * but this is highly discouraged because of potential incompatibility with
 * other libraries.
 */
public final class SRP6PrivateKey implements SRP6IntegerVariable {
    /** SRP-6 Integer Variable: private key (x). */
    private final SRP6IntegerVariable privateKey;

    /**
     * Constructs a new SRP-6 Private Key as specified in RFC 2945.
     * <pre>
     * x = H(s | H(I | ":" | P))
     * </pre>
     *
     * @param hashFunction a one-way hash function - H()
     * @param salt SRP-6 variable: salt (s)
     * @param cleartextUsername SRP-6 variable: cleartext username -
     *                          identity (I)
     * @param cleartextPassword SRP-6 variable: cleartext password (P)
     * @param endianness the byte order to use when converting the resulting
     *                   hash to integer
     */
    public SRP6PrivateKey(
        final ImmutableMessageDigest hashFunction,
        final Bytes salt,
        final Bytes cleartextUsername,
        final Bytes cleartextPassword,
        final ByteOrder endianness
    ) {
        this(
            hashFunction,
            salt,
            new Hash(
                hashFunction,
                cleartextUsername,
                new PlainText(":"),
                cleartextPassword
            ),
            endianness
        );
    }

    /**
     * Constructs a new SRP-6 Private Key from {@code salt} and
     * {@code password}.
     * <pre>
     * x = H(s | p)
     * </pre>
     *
     * @param hashFunction a one-way hash function - H()
     * @param salt SRP-6 variable: salt (s)
     * @param password SRP-6 variable: password (p)
     * @param endianness the byte order to use when converting the resulting
     *                   hash to integer
     */
    public SRP6PrivateKey(
        final ImmutableMessageDigest hashFunction,
        final Bytes salt,
        final Bytes password,
        final ByteOrder endianness
    ) {
        this(
            new SRP6PresetIntegerVariable(
                new Hash(hashFunction, salt, password),
                endianness
            )
        );
    }

    /**
     * Constructs a new SRP-6 Private Key from {@code args}.
     * <pre>
     * x = H(args)
     * </pre>
     * <p>
     * Use this constructor if none of the other constructors are compatible
     * with your version of the protocol.
     *
     * @param hashFunction a one-way hash function - H()
     * @param args custom arguments
     * @param endianness the byte order to use when converting the resulting
     *                   hash to integer
     */
    public SRP6PrivateKey(
        final ByteOrder endianness,
        final ImmutableMessageDigest hashFunction,
        final Bytes... args
    ) {
        this(
            new SRP6PresetIntegerVariable(
                new Hash(hashFunction, args),
                endianness
            )
        );
    }

    /**
     * Hidden constructor.
     * <p>
     * Users should not be able to set this variable directly. Constants and
     * preset values must be created by using the
     * {@link SRP6PresetIntegerVariable} class.
     *
     * @param privateKey SRP-6 Integer Variable: private key (x)
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    private SRP6PrivateKey(final SRP6IntegerVariable privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return privateKey.bytes(preferredOrder);
    }
}
