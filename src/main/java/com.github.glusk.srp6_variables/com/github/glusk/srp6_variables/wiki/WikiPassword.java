package com.github.glusk.srp6_variables.wiki;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;

/** Wikipedia Python example variable: cleartext password (P). */
public final class WikiPassword implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new PlainText("password1234");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
