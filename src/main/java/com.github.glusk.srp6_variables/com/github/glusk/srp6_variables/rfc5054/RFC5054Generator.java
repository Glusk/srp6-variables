package com.github.glusk.srp6_variables.rfc5054;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6Variable;
import com.github.glusk.srp6_variables.SRP6PresetVariable;

/** RFC5054 Test Vector: generator (g). */
public final class RFC5054Generator implements SRP6Variable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6Variable VALUE =
        new SRP6PresetVariable(
            new Hex("2"),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
