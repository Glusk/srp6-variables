package com.github.glusk.srp6_variables.mozilla;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** Mozilla Test Vector: srpK (K). */
public final class MozillaSessionKey extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "e68fd0112bfa31dc ffc8e9c96a1cbadb"
          + "4c3145978ff35c73 e5bf8d30bbc7499a"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
