package com.github.glusk.srp6_variables.mozilla;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;

/** Mozilla Test Vector: cleartext username - identity (I). */
public final class MozillaIdentity implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new PlainText("andré@example.org");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
