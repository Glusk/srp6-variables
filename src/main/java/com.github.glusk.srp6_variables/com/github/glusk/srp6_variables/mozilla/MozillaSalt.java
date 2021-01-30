package com.github.glusk.srp6_variables.mozilla;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** Mozilla Test Vector: srpSalt (s). */
public final class MozillaSalt implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "00f1000000000000 0000000000000000"
          + "0000000000000000 0000000000000179"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
