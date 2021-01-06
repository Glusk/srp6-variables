package com.github.glusk.srp6_variables.wow;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** WoW Test Vector: session key (K). */
public final class WoWSessionKey implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "3D41C92C 4D1F32BA DB7B2D41 3B6E67BC 1A8C483C"
          + "DE6FFD0D 555F922B 28617941 D6B4E394 2842E629"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
