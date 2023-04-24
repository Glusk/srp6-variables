package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: client ephemeral private key (a). */
public final class MozillaClientPrivateEphemeral
    extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "00f2000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 000000000000d3d7"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
