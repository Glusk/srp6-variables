package com.github.glusk.srp6_variables;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.Arrays;

import com.github.glusk.caesar.Bytes;

/**
 * An SRP-6 variable.
 * <p>
 * Objects that implement this interface represent SRP-6 variables. The full
 * list can be seen in the
 * {@link com.github.glusk.srp6_variables package docs}.
 * <h2>Mapping between integers and byte sequences</h2>
 * RFC2945 specifies a mapping (which RFC5054 follows) between byte
 * sequences and integers:
 * <p>
 * <q>
 * <br>
 * ... <br>
 * An n-byte string S can be converted to an integer as follows: <br>
 * {@code
 *  i = S[n-1] + 256 * S[n-2] + 256^2 * S[n-3] + ... + 256^(n-1) * S[0]
 * } <br>
 * ... <br>
 * </q>
 * <p>
 * Although the above mapping assumes big-endian byte order, objects
 * implementing this interface must provide a little-endian representation
 * as well. SRP-6 variables can be viewed both as little-endian and big-endian
 * byte sequences and it is up to the protocol implementor which representation
 * to use.
 */
@FunctionalInterface
public interface SRP6Variable {
    /**
     * Returns {@code this} SRP6Variable as a byte sequence in the preferred
     * byte order.
     * <p>
     * The representation returned must be <em>minimal</em>. That is, all
     * leading (or trailing, depending on the {@code preferredOrder}) zero
     * bytes have to be trimmed.
     * <p>
     * Number zero is hence defined as an empty byte sequence.
     *
     * @param preferredOrder the preferred byte order of the byte sequence
     *                       that represents {@code this} SRP6Variable
     * @return the byte sequence that represents {@code this} SRP6Variable in
     *         the preferred byte order
     */
    Bytes bytes(ByteOrder preferredOrder);

    /**
     * Returns a new byte sequence as a zero-padded result of
     * {@link #bytes(ByteOrder) this.bytes(preferredOrder)}.
     * <p>
     * If {@code preferredOrder} equals to {@code ByteOrder.BIG_ENDIAN},
     * the sequence gets padded from the front, otherwise from the back.
     *
     * @param preferredOrder the preferred byte order of the byte sequence
     *                       that represents {@code this} SRP6Variable
     * @param length the length of the zero-padded byte sequence to return
     * @return a new zero-padded byte sequence that represents {@code this}
     *         SRP6Variable in the preferred byte order
     * @throws SRP6PaddingException if {@code length} is less than or equal to
     *                              the length of the sequence returned by
     *                     {@link #bytes(ByteOrder) this.bytes(preferredOrder)}
     */
    default Bytes bytes(ByteOrder preferredOrder, int length)
        throws SRP6PaddingException {
        Bytes minimal = bytes(preferredOrder);
        if (length < minimal.asArray().length) {
            throw new SRP6PaddingException(
                String.format(
                    "Loss of information! The desired padding length (%d) is "
                  + "less than the minimal byte length required to represent "
                  + "this SRP6Variable (%d).",
                    length,
                    minimal.asArray().length
                )
            );
        }

        // check which end to zero-pad
        if (preferredOrder.equals(ByteOrder.BIG_ENDIAN)) {
            minimal = minimal.reversed();
        }

        // perform zero-padding
        byte[] tmp = minimal.asArray();
        Bytes zeroPadded = () -> Arrays.copyOfRange(tmp, 0, length);

        // return the result
        if (preferredOrder.equals(ByteOrder.BIG_ENDIAN)) {
           return zeroPadded.reversed();
        }
        return zeroPadded;
    }

    /**
     * Returns this SRP6Variable as a non-negative BigInteger.
     * <p>
     * This method call is equivalent to:
     * <pre>
     * new BigInteger(1, bytes(ByteOrder.BIG_ENDIAN).asArray())
     * </pre>
     *
     * @return {@code this} SRP6Variable as a non-negative BigInteger
     */
    default BigInteger asNonNegativeBigInteger() {
        return new BigInteger(1, bytes(ByteOrder.BIG_ENDIAN).asArray());
    }

    /**
     * Returns {@code true} if and only if the minimal byte array
     * representation of SRP-6 Variables {@code this} and {@code that} equals.
     *
     * @param that the object to compare against
     * @return {@code true} if SRP-6 Variables equal, {@code false} otherwise
     */
    default boolean equals(SRP6Variable that) {
        return
            Arrays.equals(
                this.bytes(ByteOrder.BIG_ENDIAN).asArray(),
                that.bytes(ByteOrder.BIG_ENDIAN).asArray()
            );
    }
}
