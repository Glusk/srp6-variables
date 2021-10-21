package com.github.glusk.srp6_variables.wiki;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.PlainText;

/** Wikipedia Python example variable: cleartext username - identity (I). */
public final class WikiIdentity extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new PlainText("person");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
