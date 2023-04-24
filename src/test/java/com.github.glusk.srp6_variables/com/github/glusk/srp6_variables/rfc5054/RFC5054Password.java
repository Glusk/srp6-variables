package com.github.glusk.srp6_variables.rfc5054;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;

/** RFC5054 Test Vector: cleartext password (P). */
public final class RFC5054Password extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new PlainText("password123");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
