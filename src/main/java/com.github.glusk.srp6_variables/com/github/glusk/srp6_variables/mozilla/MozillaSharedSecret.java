package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: shared secret (S). */
public final class MozillaSharedSecret
    implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "0092aaf0f527906a a5e8601f5d707907 a03137e1b601e04b"
              + "5a1deb02a981f4be 037b39829a27dba5 0f1b27545ff2e287"
              + "29c2b79dcbdd32c9 d6b20d340affab91 a626a8075806c26f"
              + "e39df91d0ad979f9 b2ee8aad1bc783e7 097407b63bfe58d9"
              + "118b9b0b2a7c5c4c debaf8e9a460f4bf 6247b0da34b760a5"
              + "9fac891757ddedca f08eed823b090586 c63009b2d740cc9f"
              + "5397be89a2c32cdc fe6d6251ce11e44e 6ecbdd9b6d93f30e"
              + "90896d2527564c7e b9ff70aa91acc0ba c1740a11cd184ffb"
              + "989554ab58117c21 96b353d70c356160 100ef5f4c28d19f6"
              + "e59ea2508e8e8aac 6001497c27f362ed bafb25e0f045bfdf"
              + "9fb02db9c908f103 40a639fe84c31b27"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
