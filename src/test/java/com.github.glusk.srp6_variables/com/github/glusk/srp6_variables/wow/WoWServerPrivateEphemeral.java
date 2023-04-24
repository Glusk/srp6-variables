package com.github.glusk.srp6_variables.wow;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** WoW Test Vector: server ephemeral private key (b). */
public final class WoWServerPrivateEphemeral
    extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "86C4C539 C8BDA1F6 50CAB032 199959D4"
              + "9E53E953 9F4F9705 B2C710B2 2448D96D"
            ),
            ByteOrder.LITTLE_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
