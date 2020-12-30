package com.github.glusk.srp6_variables.rfc5054;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6PresetIntegerVariable;

/** RFC5054 Test Vector: client ephemeral public key (A). */
public final class RFC5054ClientPublicKey implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6PresetIntegerVariable(
            new Hex(
                "61D5E490 F6F1B795 47B0704C 436F523D D0E560F0 C64115BB"
              + "72557EC4 4352E890 3211C046 92272D8B 2D1A5358 A2CF1B6E"
              + "0BFCF99F 921530EC 8E393561 79EAE45E 42BA92AE ACED8251"
              + "71E1E8B9 AF6D9C03 E1327F44 BE087EF0 6530E69F 66615261"
              + "EEF54073 CA11CF58 58F0EDFD FE15EFEA B349EF5D 76988A36"
              + "72FAC47B 0769447B"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
