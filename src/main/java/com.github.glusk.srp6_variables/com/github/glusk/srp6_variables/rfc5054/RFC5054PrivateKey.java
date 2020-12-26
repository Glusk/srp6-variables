package com.github.glusk.srp6_variables.rfc5054;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6Variable;
import com.github.glusk.srp6_variables.SRP6PresetVariable;

/** RFC5054 Test Vector: private key (x). */
public final class RFC5054PrivateKey implements SRP6Variable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6Variable VALUE =
        new SRP6PresetVariable(
            new Hex("94B7555A ABE9127C C58CCF49 93DB6CF8 4D16C124"),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
