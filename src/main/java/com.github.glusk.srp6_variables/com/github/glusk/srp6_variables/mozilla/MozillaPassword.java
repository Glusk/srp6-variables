package com.github.glusk.srp6_variables.mozilla;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/**
 * Mozilla Test Vector: srpPW (P).
 * <p>
 * Mozilla utilises key-stretching and scrypt on plain-text password:
 * {@code "pässwörd"} to produce a value that can be fed to:
 * {@link com.github.glusk.srp6_variables.SRP6PrivateKey#SRP6PrivateKey(
 *  ImmutableMessageDigest, Bytes, Bytes, Bytes, ByteOrder)
 *  new SRP6PrivateKey(hashFunction, salt, username, password, endianness)}
 * as the {@code password} argument.
 * <p>
 * This SRP-6 Variable represents that final stage, as key-stretching and
 * scrypt algorithm are out of scope of this project.
 */
public final class MozillaPassword implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "00f9b71800ab5337 d51177d8fbc682a3"
          + "653fa6dae5b87628 eeec43a18af59a9d"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
