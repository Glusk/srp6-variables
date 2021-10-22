package com.github.glusk.srp6_variables.mozilla;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** Mozilla Test Vector: client session proof (M1). */
public final class MozillaClientSessionProof extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "27949ec1e0f16256 33436865edb037e2"
          + "3eb6bf5cb91873f2 a2729373c2039008"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
