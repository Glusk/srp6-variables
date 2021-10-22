package com.github.glusk.srp6_variables.wow;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** WoW Test Vector: server session proof (M2). */
public final class WoWServerSessionProof extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex("1EEA742C 32C30B49 EA63161E 91C38B55 25C71CA7");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
