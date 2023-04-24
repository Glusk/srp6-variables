package com.github.glusk.srp6_variables.mozilla;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/**
 * Mozilla Test Vector: client session proof (M2).
 * <p>
 * This value was derived from other test vectors - Mozilla does not
 * calculate it!
 */
public final class MozillaServerSessionProof extends AbstractBytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "44622786EDA66DC4 7C60A792AA801ED5"
          + "0148C710086EC896 3159AE2F61B61002"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
