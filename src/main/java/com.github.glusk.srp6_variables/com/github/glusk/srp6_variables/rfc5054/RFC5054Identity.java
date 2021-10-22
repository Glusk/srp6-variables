package com.github.glusk.srp6_variables.rfc5054;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;

/** RFC5054 Test Vector: cleartext username - identity (I). */
public final class RFC5054Identity extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new PlainText("alice");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
