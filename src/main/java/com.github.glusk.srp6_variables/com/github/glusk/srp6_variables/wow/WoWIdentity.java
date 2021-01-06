package com.github.glusk.srp6_variables.wow;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;

/** WoW Test Vector: cleartext username - identity (I). */
public final class WoWIdentity implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new PlainText("TEST");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
