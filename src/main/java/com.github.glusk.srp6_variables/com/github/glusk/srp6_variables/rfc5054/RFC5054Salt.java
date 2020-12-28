package com.github.glusk.srp6_variables.rfc5054;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** RFC5054 Test Vector: salt (s). */
public final class RFC5054Salt implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex("BEB25379 D1A8581E B5A72767 3A2441EE");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
