package com.github.glusk.srp6_variables.wow;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** WoW Test Vector: client session proof (M1). */
public final class WoWClientSessionProof implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex("C80C3013 11D04379 F0D00393 DF0D478A 6EEC2D00");

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
