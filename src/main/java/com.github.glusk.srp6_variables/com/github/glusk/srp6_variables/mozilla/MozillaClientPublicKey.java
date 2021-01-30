package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: client public key (A). */
public final class MozillaClientPublicKey
    implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "007da76cb7e77af5 ab61f334dbd5a958 513afcdf0f47ab99"
              + "271fc5f7860fe213 2e5802ca79d2e5c0 64bb80a38ee08771"
              + "c98a937696698d87 8d78571568c98a1c 40cc6e7cb101988a"
              + "2f9ba3d65679027d 4d9068cb8aad6ebf f0101bab6d52b5fd"
              + "fa81d2ed48bba119 d4ecdb7f3f478bd2 36d5749f2275e948"
              + "4f2d0a9259d05e49 d78a23dd26c60bfb a04fd346e5146469"
              + "a8c3f010a627be81 c58ded1caaef2363 635a45f97ca0d895"
              + "cc92ace1d09a99d6 beb6b0dc0829535c 857a419e834db128"
              + "64cd6ee8a843563b 0240520ff0195735 cd9d316842d5d3f8"
              + "ef7209a0bb4b54ad 7374d73e79be2c39 75632de562c59647"
              + "0bb27bad79c3e2fc ddf194e1666cb9fc"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
