package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: server ephemeral private key (b). */
public final class MozillaServerPrivateEphemeral
    implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "00f3000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 0000000000000000 0000000000000000"
              + "0000000000000000 000000000000000f"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
