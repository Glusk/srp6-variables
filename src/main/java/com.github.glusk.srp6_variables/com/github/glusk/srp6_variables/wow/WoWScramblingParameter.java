package com.github.glusk.srp6_variables.wow;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** WoW Test Vector: random scrambling parameter (u).*/
public final class WoWScramblingParameter extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex("698F9038 2D1313D5 FB888B34 F8694BA9 5E4309E3"),
            ByteOrder.LITTLE_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
